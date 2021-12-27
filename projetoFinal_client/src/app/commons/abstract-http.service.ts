import { Injectable } from '@angular/core';


import { HttpClient, HttpHeaders,HttpErrorResponse,  HttpParams } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { catchError, retry, throwError } from 'rxjs';

@Injectable()
export class AbstractHttpService {

    protected url: string = "http://localhost:8080/";

    private extractData: any;

    constructor(private http: HttpClient) { }

    protected getMethod<T>(relativePath: string = '', params: any = null) {
        if (params != null) {

            return this.http.get<T>(this.url + relativePath, {
                params: params
            });
        } else {
            return this.http.get<T>(this.url + relativePath);
        }
    }

    protected postMethod<T>(value: any, relativePath: string = '') {
        console.log('Post Method: ' + this.url + relativePath);
        return this.http.post<T>(this.url + relativePath, value, { headers: this.getHeaders() });
    }

    protected putMethod(value: any, relativePath: string = '') {
      return this.http.put(this.url, JSON.stringify(value), { headers: this.getHeaders() })
      .pipe(
        retry(2),
        catchError(this.handleError)
      )
    }

    protected deleteMethod<T>(value: any, relativePath: string = '') {
      return this.http.delete<T>(this.url +  relativePath+"/", { headers: this.getHeaders() })
      .pipe(
        retry(1),
        catchError(this.handleError)
      )
    }

    protected getHeaders() {
        const headers = new HttpHeaders();
        headers.set('Content-Type', 'application/json');
        return headers;
    }

    // Manipulação de erros
    handleError(error: HttpErrorResponse) {
      let errorMessage = '';
      if (error.error instanceof ErrorEvent) {
        // Erro ocorreu no lado do client
        errorMessage = error.error.message;
      } else {
        // Erro ocorreu no lado do servidor
        errorMessage = `Código do erro: ${error.status}, ` + `menssagem: ${error.message}`;
      }
      console.log(errorMessage);
      return throwError(errorMessage);
    };

}
