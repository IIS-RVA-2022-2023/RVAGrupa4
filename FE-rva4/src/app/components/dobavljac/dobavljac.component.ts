import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Dobavljac } from 'src/app/models/dobavljac';
import { DobavljacService } from 'src/app/services/dobavljac.service';
import { DobavljacDialogComponent } from '../dialogs/dobavljac-dialog/dobavljac-dialog.component';

@Component({
  selector: 'app-dobavljac',
  templateUrl: './dobavljac.component.html',
  styleUrls: ['./dobavljac.component.css']
})
export class DobavljacComponent {
  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'adresa', 'kontakt', 'actions'];
  dataSource!: MatTableDataSource<Dobavljac>;
   /* @ViewChild(MatSort, {static: false}) sort!: MatSort;
   @ViewChild(MatPaginator, {static: false}) paginator!: MatPaginator;*/

  constructor(private dobavljacService: DobavljacService, private dialog: MatDialog) { }

  ngOnInit(): void { this.loadData(); }
  ngOnChanges(): void { this.loadData(); }

  public loadData() {
    this.subscription = this.dobavljacService.getAllDobavljaci().subscribe(
      data => {
        //console.log(data);
        this.dataSource = new MatTableDataSource(data);
        /*this.dataSource.sort = this.sort;
        this.dataSource.paginator = this.paginator;*/
      },
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  //iz htmla prosledjujemo ove podatke dijalogu
  openDialog(flag: number, dobavljac?: Dobavljac): void {
    const dialogRef = this.dialog.open(DobavljacDialogComponent, { data: (dobavljac ? dobavljac : new Dobavljac()) });
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
}
