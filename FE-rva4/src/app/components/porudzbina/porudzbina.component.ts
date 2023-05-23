import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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

  constructor(private porudzbinaService: PorudzbinaService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.porudzbinaService.getAllPorudzbine().subscribe(
      data => {
        this.dataSource = new MatTableDataSource(data)
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

  applyFilter(filterValue: any) {
    filterValue = filterValue.target.value
    filterValue = filterValue.trim();
    filterValue = filterValue.toLocaleLowerCase();
    this.dataSource.filter = filterValue; //    JaBuKa    --> JaBuKa --> jabuka
  }
}
