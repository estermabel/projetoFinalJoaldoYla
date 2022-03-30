import { CasoTeste } from 'src/app/model/casoTeste';
import { DialogRespostaComponent } from './../dialog-resposta/dialog-resposta.component';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { Tarefa } from 'src/app/model/tarefa';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cadastrar-resposta',
  templateUrl: './cadastrar-resposta.component.html',
  styleUrls: ['./cadastrar-resposta.component.css']
})
export class CadastrarRespostaComponent implements OnInit {
  testes = new MatTableDataSource<CasoTeste>();
  displayedColumns = [
    'entrada',
    'saida'
  ];

  tarefa = new TarefaDTO();

  constructor(private tarefaService: TarefaService,
    public dialog: MatDialog,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService,
  ) { }

  ngOnInit(): void {
    this.buscarTarefa();
  }

  buscarTarefa(){
    this.tarefa = this.storage.get("tarefa");
    this.tarefaService.findOne(this.tarefa.id).subscribe((data) => {
      this.tarefa = data;
      console.log(this.tarefa);
    });
  }

  openDialogResposta(){
    const dialogRef = this.dialog.open(DialogRespostaComponent, {
      width: '800px'
    });
  }
}
