import { LoginDTO } from './../../model/DTO/loginDTO';

import { Router } from '@angular/router';
import { Component, Inject, OnInit } from '@angular/core';
import { AccountService } from 'src/app/account/service/account.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router:Router,
    private accountService: AccountService,
    @Inject(SESSION_STORAGE) private storage: StorageService
  ) { }


  usuario = new  LoginDTO();


  mensagemErro: string= ""
  formLogin: any
  ngOnInit(): void {
    this.accountService.clearAuthentication();
    this.formLogin =  new FormGroup({
      login: new FormControl([Validators.required]),
      senha: new FormControl([Validators.required]),
    });

  }

  login(){
      if(this.validarCampos()){
        this.accountService.login(this.usuario).subscribe(data =>{
          const token = JSON.parse(JSON.stringify(data)).token;
          this.storage.set("token", token);
          //console.log('login efetuado: ', this.storage.get("token"))
          this.mensagemErro = "";
          this.router.navigate(['tarefas']);
        }, (error)=>{
          console.error("Erro ao fazer login");
          this.mensagemErro = "Login ou Senha Inválidos";
        }
        )
      }else{
        this.mensagemErro = "Preencha todos os campos";
      }
  }

  validarCampos(): boolean{
    const formulario = this.formLogin.value;
    if(formulario.login == null || formulario.login === ''){
      return false;
    }

    if(formulario.senha == null || formulario.senha === ''){
      return false;
    }

    return true;

  }
}
