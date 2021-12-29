import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GenericService } from 'src/app/commons/generic.service';

@Injectable({
  providedIn: 'root'
})
export class TarefaService extends GenericService {

  constructor(http: HttpClient) {
    super(http);
  }
}
