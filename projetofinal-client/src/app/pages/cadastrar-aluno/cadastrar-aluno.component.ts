import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-cadastrar-aluno',
  templateUrl: './cadastrar-aluno.component.html',
  styleUrls: ['./cadastrar-aluno.component.css']
})
export class CadastrarAlunoComponent implements OnInit {

  constructor(private usuarioService: UsuarioService,
    private router: Router,) { }



  usuario = new  UsuarioDTO();
  formCadastrarUsuario: any
  ngOnInit(): void {
    this.formCadastrarUsuario =  new FormGroup({
      nome: new FormControl([Validators.required]),
      login: new FormControl([]),
      senha: new FormControl([Validators.required])
    });
  }

  cadastrarUsuario(){
    this.usuario.perfilId = 2
    if(this.formCadastrarUsuario.valid){
      this.usuarioService.save(this.usuario).subscribe(data =>{
        console.log("cadastrado com sucesso", data);
      }, (error) =>{
        console.log(error.error);
      }
      )
      this.router.navigate(["login"])
    }
  }

  voltar(){
    this.router.navigate(["login"])
  }

}
