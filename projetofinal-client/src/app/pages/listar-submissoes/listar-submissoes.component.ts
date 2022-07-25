import { Resposta } from './../../model/resposta';
import { AccountService } from 'src/app/account/_service/account.service';
import { RespostaService } from './../../service/resposta/resposta.service';
import { RespostaDTO } from './../../model/DTO/RespostaDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-listar-submissoes',
  templateUrl: './listar-submissoes.component.html',
  styleUrls: ['./listar-submissoes.component.css']
})
export class ListarSubmissoesComponent implements OnInit {

  constructor(
    private respostaService: RespostaService,
    private accountService: AccountService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService,
  ) { }

  respostas=  new MatTableDataSource<RespostaDTO>();
  teste: Array<RespostaDTO>  = []

  displayedColumns = [
    'atividade',
    'data',
    'acoes'
  ];

  ngOnInit(): void {
    let user = this.accountService.getSubject()
    this.respostaService.listarPorUsuario(user).subscribe((data) => {
      this.respostas.data=data
      this.teste = data
      //console.log(this.respostas.data)
      //descobrir pq não ta vindo o resultado

    })



  }

  detalhar(resposta: Resposta){
    this.storage.set("respostaEnviada", resposta);
    this.router.navigate(["resultado"]);
  }

}
