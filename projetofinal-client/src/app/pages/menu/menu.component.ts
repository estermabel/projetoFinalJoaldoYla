import { UsuarioService } from './../../service/usuario/usuario.service';
import { UsuarioDTO } from './../../model/DTO/usuarioDTO';
import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/account/_service/account.service';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  usuarioLogado = new UsuarioDTO()

  constructor(private accountService: AccountService,
    private usuarioService: UsuarioService,
    private router: Router) { }


  ngOnInit(): void {
    this.buscarUsuario();
  }

  buscarUsuario(){
    let id  = this.accountService.getSubject()
    this.usuarioService.findOne(id).subscribe((data) => {
      this.usuarioLogado = data;
      console.log(this.usuarioLogado);
    });
  }

  perfil(){

  }

  sair(){
    this.accountService.clearAuthentication();
    this.router.navigate(["login"])
  }

  listarUsuarios(){
    this.router.navigate(["usuarios"])
  }

  listarTarefas(){

    this.router.navigate(["tarefas"])
  }

  cadastrarUsuarios(){
    this.router.navigate(["cadastrarUsuario"])
  }

  cadastrarTarefas(){
    this.router.navigate(["cadastrarTarefa"])
  }
}
