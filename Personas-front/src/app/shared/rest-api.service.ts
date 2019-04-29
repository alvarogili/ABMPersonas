// guide https://www.positronx.io/angular-7-httpclient-http-service/

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Persona } from '../Persona';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Filtro } from '../Filtro';
import { BehaviorSubject } from 'rxjs';

@Injectable()
export class RestApiService {

   // for tests
   // apiURL = 'http://localhost:3000/personas';

  apiURL = '/personas/api/';

  private messageSource = new BehaviorSubject('vacio');
  accion = this.messageSource.asObservable();

  constructor(private http: HttpClient) { }

   // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  actualizarMensaje(newMensaje: string) {
    this.messageSource.next(newMensaje);
  }

  createSeachURL(filtro: Filtro) {
    let customURL = this.apiURL;
    let nombreNotNull = false;
    if (filtro.perNombre !== '' || filtro.perTipoDocumento !== '') {
      customURL = customURL + '?';
    }
    if (filtro.perNombre !== '') {
      customURL = customURL + 'perNombre=' + filtro.perNombre;
      nombreNotNull = true;
    }
    if (filtro.perTipoDocumento !== '') {
      if (nombreNotNull) {
        customURL = customURL + '&';
      }
      customURL = customURL + 'perTipoDocumento=' + filtro.perTipoDocumento;
    }
    console.warn(customURL);
    return customURL;
  }

  getPersonas(filtro: Filtro) {
    const response = this.http.get<Persona[]>(this.createSeachURL(filtro), {headers: this.httpOptions.headers});
    return response;
  }

  savePersona(persona: Persona): Observable<Persona> {
    let nuevaPersona = {
      "perNombre": persona.perNombre,
      "perApellido": persona.perApellido,
      "perFechaNacimiento": persona.perFechaNacimiento,
      "perNumeroDocumento" : persona.perNumeroDocumento,
      "perTipoDocumento" : persona.perTipoDocumento
    };
    return this.http.post<Persona>(this.apiURL, nuevaPersona)
    .pipe(
      catchError(err => this.handleError(err))
    );
  }

  deletePersona(id) {
    return this.http.delete(this.apiURL + id, {headers: this.httpOptions.headers});
  }

  private handleError(error: HttpErrorResponse) {
     if (error.error instanceof ErrorEvent) {
       // A client-side or network error occurred. Handle it accordingly.
       console.error('An error occurred:', error.error.message);
     } else {
       // The backend returned an unsuccessful response code.
       // The response body may contain clues as to what went wrong,
       console.error(
         `Backend returned code ${error.status}, ` +
         `body was: ${error.error}`);
     }
     // return an observable with a user-facing error message
     return throwError(
       'Something bad happened; please try again later.');
  }
}
