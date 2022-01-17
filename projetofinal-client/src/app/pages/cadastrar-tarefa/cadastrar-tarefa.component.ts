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


@Component({
  selector: 'app-cadastrar-tarefa',
  templateUrl: './cadastrar-tarefa.component.html',
  styleUrls: ['./cadastrar-tarefa.component.css']
})
export class CadastrarTarefaComponent implements OnInit {

  casosTestes = new MatTableDataSource<CasoTeste>();

  casoTeste = new CasoTesteDTO();
  tarefa = new  TarefaDTO();

  displayedColumns = [
    'nomeTeste',
    'entrada',
    'saida'
  ];
  constructor(
    public tarefaService: TarefaService,
    public dialog: MatDialog,
    private detectorRef: ChangeDetectorRef,
    private casoTesteService: CasoTesteService,
    @Inject(SESSION_STORAGE) private storage: StorageService  ) { }

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

  adicionarCasoTeste(){

    this.casoTeste.nomeTeste = "teste"
    this.casoTeste.entrada = "entrada teste"
    this.casoTeste.saida = "saida teste"
    this.casoTeste.comparacao = 1

  }

  openDialog() {
    const dialogRef = this.dialog.open(DialgogComponent);

    dialogRef.afterClosed().subscribe(result => {

      this.casoTeste = this.storage.get("teste");
      const data = this.casosTestes.data;
      data.push(this.casoTeste)
      this.casosTestes.data = data
    });
  }



  addData(){
    /*
    this.socket.on('newMessage', function(event) {
    const data = this.dataSource.data;
    data.push(event);
    this.dataSource.data = data;
    });
    */
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



}
