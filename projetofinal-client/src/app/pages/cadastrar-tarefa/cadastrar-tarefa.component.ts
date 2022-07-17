import { CasoTeste } from 'src/app/model/casoTeste';
import { DialogCasoTesteComponent } from '../dialog-caso-teste/dialog-caso-teste.component';
import { CasoTesteService } from './../../service/caso-teste/caso-teste.service';
import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { ChangeDetectorRef, Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTable, MatTableDataSource } from '@angular/material/table';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { DialogEditarCasoTesteComponent } from '../dialog-editar-caso-teste/dialog-editar-caso-teste.component';


@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.css']
})
export class CadastrarTarefaComponent implements OnInit {

  casosTestes = new MatTableDataSource<CasoTeste>();
  stausDialog = "";
  casoTeste = new CasoTesteDTO();

  tarefa = new  TarefaDTO();

  displayedColumns = [
    'nomeTeste',
    'entrada',
    'saida',
    'comparacao',
    'flagExibir',
    'acoes'
  ];

  comparacoes = [
    {value: 0, viewValue: "Igual"},
    {value: 1, viewValue: "Igual ignorando case sensitive"},
    {value: 2, viewValue: "Contém"},
  ]

  /*formTarefa = this.formBuilder.group({
    titulo: new FormControl("", Validators.required),
    descricao: new FormControl("", Validators.required),
    dataEntrega: new FormControl("", Validators.required),
  });*/

  constructor(
    public tarefaService: TarefaService,
    private formBuilder: FormBuilder,
    public dialog: MatDialog,
    private detectorRef: ChangeDetectorRef,
    private router: Router,
    private casoTesteService: CasoTesteService,
    @Inject(SESSION_STORAGE) private storage: StorageService  ) {}

  ngOnInit(): void {

  }

  cadastrarTarefa(){
    // Fri Jan 21 2022 16:08:05 GMT-0300 (Horário Padrão de Brasília)
    this.tarefa.testes = this.casosTestes.data
    this.tarefaService.save(this.tarefa).subscribe(data =>{
      console.log("cadastrado com sucesso", data);
      this.router.navigate(["tarefas"])
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
    this.tarefa.testes.push(this.casoTeste)

  }

  excluirCasoTeste(teste: CasoTeste){
    this.removerItemArray(teste, this.casosTestes)
  }

  removerItemArray(object: any, dataSource: MatTableDataSource<any>) {
    const index = dataSource.data.indexOf(object);

    if (index > -1) {
      dataSource.data.splice(index, 1);
      dataSource._updateChangeSubscription();
    }
  }

  edit (object: any, editado: CasoTeste, dataSource: MatTableDataSource<any>){
    const index = dataSource.data.indexOf(object);
    let casoTesteEditar = new CasoTesteDTO();

    if (index > -1) {
      casoTesteEditar = dataSource.data.at(index);
      casoTesteEditar.nomeTeste = editado.nomeTeste;
      casoTesteEditar.entrada = editado.entrada;
      casoTesteEditar.saida = editado.saida;
      casoTesteEditar.comparacao = editado.comparacao;
      casoTesteEditar.flagExibir = editado.flagExibir;
      //dataSource.data.splice(index, 1);
      //dataSource.data.push(this.casoTesteEditar)
      dataSource._updateChangeSubscription();
    }
  }



  editarCasoTeste(teste: CasoTeste){
    this.storage.set("testeEditar", teste);
    const dialogRef = this.dialog.open(DialogCasoTesteComponent, {
      width: '400px',
      data: {teste: teste}
    });

    dialogRef.afterClosed().subscribe(result => {
      this.storage.remove("testeEditar");
      this.stausDialog = this.storage.get("status");
      if (this.stausDialog == "editar") {
        this.casoTeste = this.storage.get("teste");
        // Then you update that record using data from dialogData (values you enetered)
        this.edit(teste, this.casoTeste, this.casosTestes)
        // And lastly refresh table
        //this.refreshTable();
      }else if(this.stausDialog == "cancelar"){
        console.log("cancelado");
      }
    });
  }

  openDialog() {
    const dialogRef = this.dialog.open(DialogCasoTesteComponent, {
      width: '400px',
    });

    dialogRef.afterClosed().subscribe(result => {
      this.stausDialog = this.storage.get("status");

      if(this.stausDialog == "cadastrar"){
        this.casoTeste = this.storage.get("teste");
        const data = this.casosTestes.data;
        data.push(this.casoTeste)
        this.casosTestes.data = data
        this.storage.remove("teste");
      }else if(this.stausDialog == "cancelar"){
        console.log("cancelado");
      }
    });
  }

  alterarExibirParaAlunor(teste: CasoTeste){
    teste.flagExibir = teste.flagExibir == false ? true : false;
    this.editExibir(teste, this.casosTestes)
  }

  editExibir(editar: CasoTeste, dataSource: MatTableDataSource<any>){
    let casoTesteEditar = new CasoTesteDTO();
    const index = dataSource.data.indexOf(editar);

    if (index > -1) {
      casoTesteEditar = dataSource.data.at(index);
      casoTesteEditar.flagExibir = editar.flagExibir;
      dataSource._updateChangeSubscription();
    }
  }

  buscarCasosTeste(){
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.casosTestes.data = data;
      console.log(this.casosTestes);
    });
  }



}
