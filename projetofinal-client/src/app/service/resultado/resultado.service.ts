import { Resultado } from './../../model/resultado';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericService } from 'src/app/commons/generic.service';

@Injectable({
  providedIn: 'root'
})
export class ResultadoService extends GenericService{
  private relativePath = 'api/resultados/';

  constructor(http: HttpClient) {
    super(http);
  }

  override findAll(): Observable<Array<Resultado>>{
    return this.getMethod(this.relativePath);
  }

  override findOne(value: number): Observable<any> {
    return this.getMethod(this.relativePath + value);
  }
}
