import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { Tarefa } from 'src/app/model/tarefa';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';

@Component({
  selector: 'app-cadastrar-resposta',
  templateUrl: './cadastrar-resposta.component.html',
  styleUrls: ['./cadastrar-resposta.component.css']
})
export class CadastrarRespostaComponent implements OnInit {
  tarefa = new TarefaDTO();

  constructor(private tarefaService: TarefaService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService,
  ) { }

  ngOnInit(): void {
    this.buscarTarefa();
  }

  buscarTarefa(){
    this.tarefaService.findOne(this.tarefa.id).subscribe((data) => {
      this.tarefa = data;
      console.log(this.tarefa);
    });
  }
}
