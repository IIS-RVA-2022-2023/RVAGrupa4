import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Artikl } from 'src/app/models/artikl';
import { ArtiklService } from 'src/app/services/artikl.service';

@Component({
  selector: 'app-artikl-dialog',
  templateUrl: './artikl-dialog.component.html',
  styleUrls: ['./artikl-dialog.component.css']
})
export class ArtiklDialogComponent {
  public flagArtDialog!: number;

  constructor(public snackBar: MatSnackBar,
    public artiklService: ArtiklService,
    @Inject(MAT_DIALOG_DATA) public dataArtikl: Artikl,
    public dialogRef: MatDialogRef<ArtiklDialogComponent>) { }

  public add(): void {
    console.log("ID je " + this.dataArtikl.id + this.dataArtikl.naziv);
    this.artiklService.addArtikl(this.dataArtikl).subscribe(() => {
      this.snackBar.open('Uspesno dodat artikl: ' + this.dataArtikl.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja novog artikla. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.artiklService.updateArtikl(this.dataArtikl).subscribe(() => {
      this.snackBar.open('Uspesno izmenjen artikl: ' + this.dataArtikl.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene artikla. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.artiklService.deleteArtikl(this.dataArtikl.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisan artikl: ' + this.dataArtikl.naziv, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja artikla. ', 'Zatvori', {
          duration: 2500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste od izmene. ', 'Zatvori', {
      duration: 1000
    })
  }
}
