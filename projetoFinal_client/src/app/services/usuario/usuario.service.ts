import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { GenericService } from '../commons/generic.service';
import { Usuario } from '../models/usuario';


@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends GenericService {
  private relativePath = '/usuarios/';

  constructor(http: HttpClient) {
    super(http);
  }


  sessao(): Observable<any> {
      return this.getMethod(this.relativePath + 'sessao');
  }

  override findAll(): Observable<Array<Usuario>>{
      return this.getMethod(this.relativePath + 'find-all');
  }
}
