import { Component } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Subscription } from 'rxjs';
import { Artikl } from 'src/app/models/artikl';
import { ArtiklService } from 'src/app/services/artikl.service';
import { ArtiklDialogComponent } from '../dialogs/artikl-dialog/artikl-dialog.component';

@Component({
  selector: 'app-artikl',
  templateUrl: './artikl.component.html',
  styleUrls: ['./artikl.component.css']
})
export class ArtiklComponent {

  subscription!: Subscription;
  displayedColumns = ['id', 'naziv', 'proizvodjaca', 'actions'];
  dataSourceArtikl!: MatTableDataSource<Artikl>;
  
  constructor(private artiklService: ArtiklService, private dialog: MatDialog) { }

  ngOnInit(): void {
    this.loadData();
  }
  loadData(): void {
    this.subscription = this.artiklService.getAllArtikls().subscribe(
      data => {
        //console.log(data);
        this.dataSourceArtikl = new MatTableDataSource(data);
      },
      error => {
        console.log(error.name + ' ' + error.message);
      }
    );
  }

  public openDialog(flag: number, artikl?:Artikl): void {
    const dialogRef = this.dialog.open(ArtiklDialogComponent, {data: artikl});
    dialogRef.componentInstance.flagArtDialog = flag;
    dialogRef.afterClosed().subscribe(res => {if(res == 1) this.loadData()});
  }

  ngOnDestroy(){
    this.subscription.unsubscribe();
  }
}
