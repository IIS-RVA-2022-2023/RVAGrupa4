import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { StavkaPorudzbineService } from 'src/app/services/stavka-porudzbine.service';
import { StavkaPorudzbine } from 'src/app/models/stavka-porudzbine';
import { Porudzbina } from 'src/app/models/porudzbina';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-stavka-porudzbine',
  templateUrl: './stavka-porudzbine.component.html',
  styleUrls: ['./stavka-porudzbine.component.css']
})
export class StavkaPorudzbineComponent implements OnInit, OnDestroy {
  displayedColumns = ['id', 'redniBroj', 'kolicina', 'jedinicaMere', 'cena', 'porudzbina', 'artikl', 'actions'];
  dataSource!: MatTableDataSource<StavkaPorudzbine>;
  subscription!: Subscription;
  @Input() selektovanaPorudzbina!: Porudzbina;

  constructor(private stavkaPorudzbineService: StavkaPorudzbineService,
    private dialog: MatDialog,
    public snackBar: MatSnackBar) { }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  ngOnInit(): void {
    this.loadData();
  }

  ngOnChanges(): void {
    if(this.selektovanaPorudzbina.id) {
       this.loadData();    }  }
       
  loadData() {
    this.subscription = this.stavkaPorudzbineService.getAllStavkeZaPorudzbinu(this.selektovanaPorudzbina.id)
      .subscribe({
        next: (data) => this.dataSource = data,
        error: (error) =>  {this.snackBar.open('Porudzbina nema stavke', 'Zatvori', {
          duration: 2500
        }); this.dataSource =  new MatTableDataSource<StavkaPorudzbine>},
        complete: () => console.info('complete') 
    })
  }
 /* public openDialog(flag: number, stavkaPorudzbine?: StavkaPorudzbine) {
    const dialogRef = this.dialog.open(StavkaPorudzbineDialogComponent, { data: (stavkaPorudzbine ? stavkaPorudzbine : new Porudzbina()) });
    dialogRef.componentInstance.flag = flag;
    if (flag === 1) {
      dialogRef.componentInstance.data.porudzbina = this.selektovanaPorudzbina;
    }
    dialogRef.afterClosed()
      .subscribe(result => {
        if (result === 1) {
          this.loadData();
        }
      })
  }*/
}