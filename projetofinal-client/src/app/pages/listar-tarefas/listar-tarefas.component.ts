import { TarefaService } from './../../service/tarefa/tarefa.service';
import { Component, OnInit } from '@angular/core';
import { Tarefa } from 'src/app/model/tarefa';
import { MatTableDataSource } from '@angular/material/table';
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
    'data de entrega'
  ];
  constructor(private tarefaService: TarefaService,
    private router: Router) { }

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
}
