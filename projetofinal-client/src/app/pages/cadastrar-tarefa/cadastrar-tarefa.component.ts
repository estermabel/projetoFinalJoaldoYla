import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { CasoTeste } from './../../model/casoTeste';
import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { CasoTesteService } from 'src/app/service/caso-teste/caso-teste.service';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';

@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.css']
})
export class CadastrarTarefaComponent implements OnInit {
  casosTestes = new MatTableDataSource<CasoTeste>();
  casoTeste = new CasoTesteDTO();
  tarefa = new  TarefaDTO();

  constructor(
    public tarefaService: TarefaService,
    public dialog: MatDialog,
    private casoTesteService: CasoTesteService
  ) { }

  ngOnInit(): void {
  }

  cadastrarTarefa(){
    this.adicionarCasoTeste()
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

  }

  addData(){
    this.adicionarCasoTeste();
    //this.casosTestes.renderRows();
  }

  removeData(){
    //this.casosTestes.renderRows();
  }

  buscarCasosTeste(){
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.casosTestes.data = data;
      console.log(this.casosTestes);
    });
  }

  openDialog() {

  }

}
