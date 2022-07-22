import { AccountService } from 'src/app/account/_service/account.service';
import { RespostaService } from './../../service/resposta/resposta.service';
import { RespostaDTO } from './../../model/DTO/RespostaDTO';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listar-submissoes',
  templateUrl: './listar-submissoes.component.html',
  styleUrls: ['./listar-submissoes.component.css']
})
export class ListarSubmissoesComponent implements OnInit {

  constructor(
    private respostaService: RespostaService,
    private accountService: AccountService
  ) { }

  respostas: Array<RespostaDTO> = [];

  ngOnInit(): void {
    let user = this.accountService.getSubject()
    this.respostaService.listarPorUsuario(user).subscribe((data) => {

      this.respostas=data
      console.log(this.respostas)
    })

  }

}
