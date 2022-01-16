import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Usuario } from 'src/app/model/usuario';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';

@Component({
  selector: 'app-listar-usuarios',
  templateUrl: './listar-usuarios.component.html',
  styleUrls: ['./listar-usuarios.component.css']
})
export class ListarUsuariosComponent implements OnInit {

  usuarios = new MatTableDataSource<Usuario>();
  displayedColumns = [
    'nome',
    'login',
    'data ultimo acesso',
    'data cadastro',
    'perfil'
  ];

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    console.log("teste")
    this.buscarUsuarios();
  }

  buscarUsuarios(){
    this.usuarioService.findAll().subscribe((data: any[]) => {
      this.usuarios.data = data;
      console.log(this.usuarios);
    });
  }
}
