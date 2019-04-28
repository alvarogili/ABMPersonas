// guide https://www.positronx.io/angular-7-httpclient-http-service/

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Persona } from '../Persona';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class RestApiService {

   // Define API
   apiURL = '/personas/api';

   constructor(private http: HttpClient) { }

   // Http Options
  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };


  getPersonas() {
    const response = this.http.get<Persona[]>(this.apiURL, {headers: this.httpOptions.headers});
    console.log(response);
    return response;
  }



   // Error handling
   handleError(error) {
    let errorMessage = '';
    if (error.error instanceof ErrorEvent) {
      // Get client-side error
      errorMessage = error.error.message;
    } else {
      // Get server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    window.alert(errorMessage);
    return throwError(errorMessage);
 }
}
