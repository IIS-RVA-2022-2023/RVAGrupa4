import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Subscription } from 'rxjs';
import { Artikl } from 'src/app/models/artikl';
import { StavkaPorudzbine } from 'src/app/models/stavka-porudzbine';
import { ArtiklService } from 'src/app/services/artikl.service';
import { StavkaPorudzbineService } from 'src/app/services/stavka-porudzbine.service';

@Component({
  selector: 'app-stavka-porudzbine-dialog',
  templateUrl: './stavka-porudzbine-dialog.component.html',
  styleUrls: ['./stavka-porudzbine-dialog.component.css']
})
export class StavkaPorudzbineDialogComponent {
  artikli!: Artikl[];
  public flag!: number;
  artiklSubscription!: Subscription;

  constructor(public snackBar: MatSnackBar,
    public dialogRef: MatDialogRef<StavkaPorudzbineDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: StavkaPorudzbine,
    public stavkaPorudzbineService: StavkaPorudzbineService,
    public artiklService: ArtiklService) { }

  ngOnDestroy(): void {
    this.artiklSubscription.unsubscribe();
  }

  ngOnInit(): void {
    this.artiklSubscription = this.artiklService.getAllArtikls()
      .subscribe(artikli => {
        this.artikli = artikli;
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
      }
  }

  compareTo(a: any, b: any) {
    return a.id === b.id;
  }

  public add(): void {
    this.stavkaPorudzbineService.addStavkaPorudzbine(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno dodata stavka porudžbine!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public update(): void {
    this.stavkaPorudzbineService.updateStavkaPorudzbine(this.data)
      .subscribe(() => {
        this.snackBar.open('Uspešno modifikovana stavka porudžbine!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public delete(): void {
    this.stavkaPorudzbineService.deleteStavkaPorudzbine(this.data.id)
      .subscribe(() => {
        this.snackBar.open('Uspešno obrisana stavka porudžbine!', 'U redu', {
          duration: 2500
        })
      }),
      (error: Error) => {
        console.log(error.name + ' ' + error.message);
        this.snackBar.open('Dogodila se greška!', 'Zatvori', {
          duration: 1500
        })
      };
  }

  public cancel(): void {
    this.dialogRef.close();
    this.snackBar.open('Odustali ste!', 'Zatvori', {
      duration: 1500
    })
  }
}
