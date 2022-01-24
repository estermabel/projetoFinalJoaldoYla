import { CasoTeste } from 'src/app/model/casoTeste';
import { DialgogComponent } from './../dialgog/dialgog.component';
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
    'comparacao'
  ];

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
    @Inject(SESSION_STORAGE) private storage: StorageService  ) { }

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

  openDialog() {
    const dialogRef = this.dialog.open(DialgogComponent, {
      width: '400px',
      data:{ teste: this.casoTeste},



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


  buscarCasosTeste(){
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.casosTestes.data = data;
      console.log(this.casosTestes);
    });
  }



}
