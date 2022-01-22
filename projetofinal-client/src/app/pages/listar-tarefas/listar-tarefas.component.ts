import { TarefaService } from './../../service/tarefa/tarefa.service';
import { Component, Inject, OnInit } from '@angular/core';
import { Tarefa } from 'src/app/model/tarefa';
import { MatTableDataSource } from '@angular/material/table';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-tarefas',
  templateUrl: './listar-tarefas.component.html',
  styleUrls: ['./listar-tarefas.component.css']
})
export class ListarTarefasComponent implements OnInit {
  tarefas = new MatTableDataSource<Tarefa>();
  displayedColumns = [
    'titulo',
    'descricao',
    'data de entrega',
    'Acoes'
  ];
  constructor(private tarefaService: TarefaService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService ) { }

  ngOnInit(): void {
    this.buscarTarefa();
  }

  buscarTarefa(){
      this.tarefaService.findAll().subscribe((data: any[]) => {
      this.tarefas.data = data;
      console.log(this.tarefas);
    });
  }

  cadastrarTarefa(){
      this.router.navigate(["cadastrarTarefa"])
  }

  respoderTarefa(tarefa: Tarefa){
      this.storage.set("tarefa", tarefa)
      this.router.navigate(["cadastrarResposta"])
  }
}
