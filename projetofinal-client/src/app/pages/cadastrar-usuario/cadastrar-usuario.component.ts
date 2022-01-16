import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit {

  constructor(private usuarioService: UsuarioService,
    private router: Router) { }
  usuario= new  UsuarioDTO
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
