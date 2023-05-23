import { StavkaPorudzbine } from './../models/stavka-porudzbine';
import { STAVKA_PORUDZBINE_URL, STAVKE_ZA_PORUDZBINU_URL } from './../app.constants';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class StavkaPorudzbineService {

  constructor(private httpClient: HttpClient) { }

  public getAllStavkeZaPorudzbinu(porId: number): Observable<any> {
    return this.httpClient.get(STAVKE_ZA_PORUDZBINU_URL+'/'+porId);
  }

  public addStavkaPorudzbine(stavkaPorudzbine: StavkaPorudzbine): Observable<any> {
    return this.httpClient.post(STAVKA_PORUDZBINE_URL, stavkaPorudzbine);
  }

  public deleteStavkaPorudzbine(id: number): Observable<any> {
    return this.httpClient.delete(STAVKA_PORUDZBINE_URL  + "/" + id);
  }

  public updateStavkaPorudzbine(stavkaPorudzbine: StavkaPorudzbine) : Observable<any>{
    return this.httpClient.put(STAVKA_PORUDZBINE_URL + "/" + stavkaPorudzbine.id, stavkaPorudzbine);  }

}