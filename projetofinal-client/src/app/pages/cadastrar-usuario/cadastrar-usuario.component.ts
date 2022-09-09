import { UsuarioDTO } from './../../model/DTO/usuarioDTO';
import { Router } from '@angular/router';
import { Component, Inject, OnDestroy, OnInit, AfterViewInit } from '@angular/core';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';


@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit, AfterViewInit {



  constructor(private usuarioService: UsuarioService,
    private router: Router,
    private formBuilder: FormBuilder,
    @Inject(SESSION_STORAGE) private storage: StorageService ) {

    }
  ngAfterViewInit(): void {
    this.storage.remove("usuario");
  }



  formCadastrarUsuario: any
  usuario = new  UsuarioDTO();
  nomeTela : string = 'Novo'
  perfis = [
    {value: 1, viewValue: "Administrador"},
    {value: 2, viewValue: "Aluno"},
    {value: 3, viewValue: "Professor"},
  ]

  ngOnInit( ): void {
      this.usuario = this.storage.get("usuario")

      this.formCadastrarUsuario =  this.formBuilder.group({
        nome:['',Validators.required],
        login: ['',Validators.required],
        senha:['', (Validators.required, Validators.minLength(6))],
        perfil:['',Validators.required],
      });

      if(this.usuario != null){
        this.usuarioService.findOne(this.usuario.id).subscribe(data=>{
          this.usuario = data
        })
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
      console.log( JSON.stringify(this.usuario));
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

      //this.usuario.perfilId = this.formCadastrarUsuario.value.perfilId
      this.usuario.dataCriacao = new Date(this.usuario.dataCriacao)
      this.usuario.dataUltimoAcesso = new Date(this.usuario.dataUltimoAcesso)
      console.log( JSON.stringify(this.usuario));
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
