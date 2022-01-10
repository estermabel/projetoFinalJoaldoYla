
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GenericService } from 'src/app/commons/generic.service';
import { Tarefa } from 'src/app/models/tarefa';

@Injectable({
  providedIn: 'root'
})
export class TarefaService extends GenericService {
  private relativePath = 'api/tarefa/';

  constructor(http: HttpClient) {
    super(http);
  }

  override findAll(): Observable<Array<Tarefa>>{
    return this.getMethod(this.relativePath);
  }

  override save(tarefa: Tarefa): Observable<any> {
    return this.postMethod(tarefa, this.relativePath);
  }

  buscarCasosTestes(id: number): Observable<any>{
    return this.getMethod(this.relativePath);
  }
}
