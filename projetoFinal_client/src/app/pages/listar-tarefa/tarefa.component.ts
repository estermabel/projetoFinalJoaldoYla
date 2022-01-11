
import { Component, Input, OnInit, ViewChild,  } from '@angular/core';
import { TarefaService } from 'src/app/services/tarefa/tarefa.service';


@Component({
  selector: 'app-tarefa',
  templateUrl: './tarefa.component.html',
  styleUrls: ['./tarefa.component.css']
})
export class TarefaComponent implements OnInit {

  @Input()  tarefas: any[] = [];
  displayedColumns = [
    'titulo',
    'descricao',
    'data de entrega'
  ];


  constructor(private tarefaService: TarefaService) { }

  ngOnInit(): void {
    console.log("component tarefas")
    this.buscarTarefas()
  }

  buscarTarefas(){
    this.tarefaService.findAll().subscribe((data: any[]) => {
      this.tarefas = data;
      console.log(this.tarefas);
    });
  }

}
