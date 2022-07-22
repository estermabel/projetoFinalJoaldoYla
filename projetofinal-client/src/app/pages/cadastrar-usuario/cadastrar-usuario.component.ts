import { UsuarioDTO } from './../../model/DTO/usuarioDTO';
import { Router } from '@angular/router';
import { Component, Inject, OnInit } from '@angular/core';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit {



  constructor(private usuarioService: UsuarioService,
    private router: Router,
    private formBuilder: FormBuilder,
    @Inject(SESSION_STORAGE) private storage: StorageService ) {

    }

    formCadastrarUsuario =  new FormGroup({
      nome: new FormControl([Validators.required]),
      login: new FormControl([Validators.required]),
      senha: new FormControl([Validators.required]),
      perfil: new FormControl(''),
    });

  usuario = new  UsuarioDTO();
  nomeTela : string = 'Novo'
  perfis = [
    {value: 0, viewValue: "Administrador"},
    {value: 1, viewValue: "Aluno"},
    {value: 2, viewValue: "Professor"},
  ]

  ngOnInit( ): void {
      this.usuario = this.storage.get("usuario")

      if(this.usuario != null){

        this.nomeTela = 'Alterar';
      }else{
        this.usuario = new UsuarioDTO();
        this.nomeTela = 'Novo';
      }
  }

  enviarForm(){
    if(this.nomeTela === 'Novo'){
      this.cadastrarUsuario();
    }else if(this.nomeTela === 'Alterar'){
      this.salvarAlteracao();
    }
  }


  cadastrarUsuario(){
    if(this.formCadastrarUsuario.valid){
      this.usuarioService.save(this.usuario).subscribe(data =>{
        console.log("cadastrado com sucesso", data);
      }, (error) =>{
        console.log(error.error);
      }
      )
      this.router.navigate(["usuarios"])
    }
  }

  salvarAlteracao(){
    if(this.formCadastrarUsuario.valid){
      this.usuarioService.update(this.usuario).subscribe(data =>{
        console.log("Atualizado com sucesso", data);
      }, (error) =>{
        console.log(error.error);
      }
      )
      this.router.navigate(["usuarios"])
    }
  }
}
