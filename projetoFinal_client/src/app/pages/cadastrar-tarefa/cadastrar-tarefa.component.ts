
import { TarefaDTO } from './../../models/DTO/tarefaDTO';
import { Tarefa } from './../../models/tarefa';
import { Component, OnInit } from '@angular/core';
import { TarefaService } from 'src/app/services/tarefa/tarefa.service';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.css']
})
export class CadastrarTarefaComponent implements OnInit {

  constructor(public tarefaService: TarefaService,
    public activeModal: NgbActiveModal,
    private modalService: NgbModal ) { }


  tarefa = new  TarefaDTO();

  ngOnInit(): void {

  }

  cadastrarTarefa(){
    this.tarefaService.save(this.tarefa).subscribe(data =>{
      console.log("cadastrado com sucesso", data);
    }, (error) =>{
      console.log(error.error);
    }
    )
  }


}
