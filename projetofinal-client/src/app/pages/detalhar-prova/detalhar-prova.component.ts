import { TarefaDTO } from './../../model/DTO/tarefaDTO';
import { Tarefa } from './../../model/tarefa';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { ProvaDTO } from './../../model/DTO/provaDTO';
import { Prova } from './../../model/prova';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { ProvaService } from 'src/app/service/prova/prova.service';

@Component({
  selector: 'app-detalhar-prova',
  templateUrl: './detalhar-prova.component.html',
  styleUrls: ['./detalhar-prova.component.css']
})
export class DetalharProvaComponent implements OnInit {
  prova = new ProvaDTO()
  tarefas: TarefaDTO[] = [];
  constructor(private provaService: ProvaService,
    private tarefaService: TarefaService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService) { }

  ngOnInit(): void {
    this.tarefaService.findAll().subscribe(data =>{
      this.tarefas = data;
    });
    this.prova = this.storage.get('prova')
    console.log(this.prova);
  }

}
