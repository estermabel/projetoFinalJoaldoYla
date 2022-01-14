import { CasoTeste } from './../../models/casoTeste';

import { TarefaDTO } from './../../models/DTO/tarefaDTO';
import { Tarefa } from './../../models/tarefa';
import { Component, OnInit, Input, ViewChild } from '@angular/core';
import { TarefaService } from 'src/app/services/tarefa/tarefa.service';
import {MatDialog, MatDialogModule} from '@angular/material/dialog';
import { NgbActiveModal, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { FormModalComponent } from '../form-modal/form-modal.component';
import { CasoTesteDTO } from 'src/app/models/DTO/CasoTesteDTO';
import { CasoTesteService } from 'src/app/services/caso-teste/caso-teste.service';
import { MatTable, MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.css']
})
export class CadastrarTarefaComponent implements OnInit {


  //@ViewChild(MatTable) table: MatTable<PeriodicElement>;
  constructor(
    public tarefaService: TarefaService,
    public dialog: MatDialog,
    private casoTesteService: CasoTesteService) {

    }
  casosTestes = new MatTableDataSource();
  casosTestesDataSource : CasoTeste[] = [];


  displayedColumns = [
    'nomeTeste',
    'entrada',
    'saida',
    'comparacao'
  ];

  tarefa = new  TarefaDTO();
  casosTeste = new Array<CasoTeste>();
  casoTeste= new CasoTesteDTO

  ngOnInit(): void {
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.casosTestes = new MatTableDataSource(data);
    });

  }

  cadastrarTarefa(){
    this.adicionarCasoTeste()
    this.tarefa.testes = this.casosTeste
    this.tarefaService.save(this.tarefa).subscribe(data =>{
      console.log("cadastrado com sucesso", data);
    }, (error) =>{
      console.log(error.error);
    }
    )
  }

  adicionarCasoTeste(){

    this.casoTeste.nomeTeste = "teste"
    this.casoTeste.entrada = "entrada teste"
    this.casoTeste.saida = "saida teste"
    this.casoTeste.comparacao = 1

    this.casosTeste.push(this.casoTeste)
  }

  addData(){
    this.adicionarCasoTeste();
    //this.casosTestes.renderRows();
  }

  removeData(){
    this.casosTeste.pop();
    //this.casosTestes.renderRows();
  }

  buscarCasosTeste(){
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.casosTestesDataSource = data;
      this.casosTestes.data = data;
      console.log(this.casosTestes);
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(FormModalComponent);

    dialogRef.afterClosed().subscribe(result => {
      console.log(`Dialog result: ${result}`);
    });
  }
}
