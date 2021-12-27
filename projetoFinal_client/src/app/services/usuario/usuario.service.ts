import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Usuario } from 'src/app/models/usuario';
import { GenericService } from 'src/app/commons/generic.service';



@Injectable({
  providedIn: 'root'
})
export class UsuarioService extends GenericService {
  private relativePath = 'api/usuarios/';

  constructor(http: HttpClient) {
    super(http);
  }


  sessao(): Observable<any> {
      return this.getMethod(this.relativePath + 'sessao');
  }

  override findAll(): Observable<Array<Usuario>>{
      return this.getMethod(this.relativePath);
  }
}
