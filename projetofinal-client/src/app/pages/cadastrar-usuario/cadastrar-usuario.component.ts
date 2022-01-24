import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit {



  constructor(private usuarioService: UsuarioService,
    private router: Router) {

    }

    formCadastrarUsuario = new FormGroup({
      nome: new FormControl([Validators.required]),
      login: new FormControl([Validators.required]),
      senha: new FormControl([Validators.required]),
      perfil: new FormControl(''),
    });

  usuario= new  UsuarioDTO

  perfis = [
    {value: 0, viewValue: "Professor"},
    {value: 1, viewValue: "Aluno"},
    {value: 2, viewValue: "Administrador"},
  ]

  ngOnInit( ): void {

  }


  cadastrarUsuario(){
    this.usuarioService.save(this.usuario).subscribe(data =>{
      console.log("cadastrado com sucesso", data);
    }, (error) =>{
      console.log(error.error);
    }
    )
    this.router.navigate(["usuarios"])
  }
}
