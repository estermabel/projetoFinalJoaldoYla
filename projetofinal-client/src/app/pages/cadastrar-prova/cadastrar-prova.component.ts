import { ProvaService } from './../../service/prova/prova.service';
import { ProvaDTO } from './../../model/DTO/provaDTO';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Tarefa } from 'src/app/model/tarefa';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { CasoTeste } from 'src/app/model/casoTeste';
import { AccountService } from 'src/app/account/_service/account.service';
import { Router } from '@angular/router';


class TarefaProva{
  id!: number;
  titulo!: string;
  descricao!: string;
  select: boolean = false;
}


@Component({
  selector: 'app-cadastrar-prova',
  templateUrl: './cadastrar-prova.component.html',
  styleUrls: ['./cadastrar-prova.component.css']
})

export class CadastrarProvaComponent implements OnInit {


  constructor(private tarefaService: TarefaService,
    private accountService: AccountService,
    private router: Router,
    private provaService: ProvaService) { }

  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  prova = new ProvaDTO()
  tarefas = new MatTableDataSource<TarefaProva>();
  tarefasIds: Array<number> = []



  displayedColumns = [
    'select',
    'titulo',
  ];

  ngOnInit(): void {
    let id = this.accountService.getSubject()
    this.tarefaService.listaPublicasProtegidasPrivadas(id).subscribe((data: any[]) => {
      this.tarefas.data = data;
      console.log(data)
    });
  }

  // ngAfterViewInit() {
  //   this.tarefas.paginator = this.paginator;
  //   this.tarefas.sort = this.sort;
  // }

  adicionarTarefa(tarefa: TarefaDTO){
    //console.log(this.tarefasIds.indexOf(tarefa.id))
    const index =this.tarefasIds.indexOf(tarefa.id)
    if( index == -1){
      this.tarefasIds.push(tarefa.id);
    }else{

      this.tarefasIds.splice(index, 1);
    }
    console.log(this.tarefasIds)
  }

  cadastrarProva(){
    this.prova.tarefas = this.tarefasIds;
    //console.log(this.prova)
    this.provaService.save(this.prova).subscribe(data=>{
      console.log("Cadastrado com sucesso", data)
    }
    )
    this.router.navigate(['provas']);
  }

}
