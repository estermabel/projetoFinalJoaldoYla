import { Usuario } from './../../model/usuario';
import { Component, Inject, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { Router } from '@angular/router';

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
    'perfil',
    'Acoes'
  ];

  constructor(private usuarioService: UsuarioService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService ) { }

  ngOnInit(): void {
    this.buscarUsuarios();
    this.storage.set("usuario", null)
  }

  buscarUsuarios(){
    this.usuarioService.findAll().subscribe((data: any[]) => {
      this.usuarios.data = data;
      console.log(this.usuarios);
    });
  }

  editarUsuario(usuario: Usuario){
    this.storage.set("usuario", usuario)
    this.router.navigate(["cadastrarUsuario"])
  }
}
