import { Component, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Porudzbina } from 'src/app/models/porudzbina';
import { PorudzbinaService } from 'src/app/services/porudzbina.service';
import { PorudzbinaDialogComponent } from '../dialogs/porudzbina-dialog/porudzbina-dialog.component';

@Component({
  selector: 'app-porudzbina',
  templateUrl: './porudzbina.component.html',
  styleUrls: ['./porudzbina.component.css']
})
export class PorudzbinaComponent {
  subscription!: Subscription;
  displayedColumns = ['id', 'datum', 'isporuceno', 'iznos', 'placeno', 'dobavljac', 'actions'];
  dataSource!: MatTableDataSource<Porudzbina>;
  selektovanaPorudzbina1!: Porudzbina;
  @ViewChild(MatSort, { static: false }) sort!: MatSort;
  @ViewChild(MatPaginator, { static: false }) paginator!: MatPaginator;

  constructor(private porudzbinaService: PorudzbinaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.porudzbinaService.getAllPorudzbine().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data);
        //sortiramo po ugnjezdenom obelezju
         this.dataSource.sortingDataAccessor = (row: Porudzbina, columnName: string): string => {
 
           console.log(row, columnName);
           if (columnName == "dobavljac") return row.dobavljac.naziv.toLocaleLowerCase();
           var columnValue = row[columnName as keyof Porudzbina] as unknown as string;
           return columnValue;
 
         }
 
         this.dataSource.sort = this.sort;
         //filtriranje po ugnjezdenom obelezju
         this.dataSource.filterPredicate = (data, filter: string) => {
           const accumulator = (currentTerm: any, key: string) => {
             return key === 'dobavljac' ? currentTerm + data.dobavljac.naziv : currentTerm + data[key as keyof Porudzbina];
           };
           const dataStr = Object.keys(data).reduce(accumulator, '').toLowerCase();
           const transformedFilter = filter.trim().toLowerCase();
           return dataStr.indexOf(transformedFilter) !== -1;
         };
 
         this.dataSource.paginator = this.paginator;
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, porudzbina?: Porudzbina): void {
    const dialogRef = this.dialog.open(PorudzbinaDialogComponent, { data: (porudzbina ? porudzbina : new Porudzbina()) });
    //otvara modalni dijalog odgovarajuće komponente
    //vracamo instancu keirane komponente dialoga
    dialogRef.componentInstance.flag = flag;
    dialogRef.afterClosed().subscribe(res => {
      if (res === 1) //uspesno 
      {
        //ponovo učitaj podatke
        this.loadData();
      }
    })
  }

  selectRow(row: any) {
    this.selektovanaPorudzbina1 = row;
  }

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}
