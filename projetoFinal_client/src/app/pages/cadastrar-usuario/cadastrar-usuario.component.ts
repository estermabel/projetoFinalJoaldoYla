import { UsuarioDTO } from './../../models/DTO/usuarioDTO';
import { Usuario } from './../../models/usuario';
import { UsuarioService } from './../../services/usuario/usuario.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cadastrar-usuario',
  templateUrl: './cadastrar-usuario.component.html',
  styleUrls: ['./cadastrar-usuario.component.css']
})
export class CadastrarUsuarioComponent implements OnInit {

  constructor(private usuarioService: UsuarioService) { }
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
  }
}
