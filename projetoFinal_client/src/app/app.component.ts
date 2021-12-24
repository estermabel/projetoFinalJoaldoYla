import { UsuarioService } from './services/usuario/usuario.service';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild,  } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Usuario } from './models/usuario';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{


  usuarios = new MatTableDataSource<Usuario>();
  displayedColumns = [
    'nome',
    'login',
    'perfil'
  ];

  title = 'projetoFinal_client';



  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  @ViewChild(MatSort)
  sort!: MatSort;

  constructor(private usuarioService: UsuarioService) {}


  ngOnInit() {
    this.buscarUsuarios();
  }


  buscarUsuarios(){
    this.usuarioService.findAll().subscribe(data => {
      this.usuarios.data = data;
      console.log(this.usuarios.data);
    });
    this.usuarios.paginator = this.paginator;
    this.usuarios.sort = this.sort;
  }

}
