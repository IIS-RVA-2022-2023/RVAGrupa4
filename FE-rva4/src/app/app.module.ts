import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { ArtiklComponent } from './components/artikl/artikl.component';
import { PorudzbinaComponent } from './components/porudzbina/porudzbina.component';
import { StavkaPorudzbineComponent } from './components/stavka-porudzbine/stavka-porudzbine.component';
import { DobavljacComponent } from './components/dobavljac/dobavljac.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatIconModule} from '@angular/material/icon';
import { MatSidenavModule } from '@angular/material/sidenav';
import { MatListModule } from '@angular/material/list';
import { MatGridListModule } from '@angular/material/grid-list';
import { MatExpansionModule } from '@angular/material/expansion';

@NgModule({
  declarations: [
    AppComponent,
    ArtiklComponent,
    PorudzbinaComponent,
    StavkaPorudzbineComponent,
    DobavljacComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    MatIconModule,
    MatSidenavModule, 
    MatListModule,
    MatGridListModule,
    MatExpansionModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
