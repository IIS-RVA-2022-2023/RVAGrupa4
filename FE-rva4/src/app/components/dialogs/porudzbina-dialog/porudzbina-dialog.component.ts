import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Dobavljac } from 'src/app/models/dobavljac';
import { Porudzbina } from 'src/app/models/porudzbina';
import { DobavljacService } from 'src/app/services/dobavljac.service';
import { PorudzbinaService } from 'src/app/services/porudzbina.service';

@Component({
  selector: 'app-porudzbina-dialog',
  templateUrl: './porudzbina-dialog.component.html',
  styleUrls: ['./porudzbina-dialog.component.css']
})
export class PorudzbinaDialogComponent {

  public flag!: number;
  public dobavljaci!: Dobavljac[];
  private subscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<PorudzbinaDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: Porudzbina,
    public porudbinaService: PorudzbinaService,
    public dobavljacService: DobavljacService) { }

  ngOnInit() {
    this.subscription = this.dobavljacService.getAllDobavljaci().subscribe(data => { this.dobavljaci = data });
  }

  compareTo(a: any, b: any) {
    return a.id == b.id;
  }

  public add(): void {
    this.porudbinaService.addPorudzbina(this.data).subscribe(() => {
      this.snackBar.open('Uspesno dodata porudzbina: ' + this.data.datum, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom dodavanja nove porudzbine. ', 'Zatvori', {
          duration: 2500
        })
      };
  }


  public update(): void {
    this.porudbinaService.updatePorudzbina(this.data).subscribe(() => {
      this.snackBar.open('Uspesno izmenjena porudzbina: ' + this.data.datum, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom izmene porudzbine. ', 'Zatvori', {
          duration: 2500
        })
      };

  }

  public delete(): void {
    this.porudbinaService.deletePorudzbina(this.data.id).subscribe(() => {
      this.snackBar.open('Uspesno obrisana porudzbina: ' + this.data.id, 'OK', {
        duration: 2500
      })
    }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message)
        this.snackBar.open('Doslo je do greske prilikom brisanja porudzbina. ', 'Zatvori', {
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
