import { UsuarioService } from './services/usuario/usuario.service';
import { Usuario } from './models/usuario';
import { MatPaginator, MatSort, MatTableDataSource } from '@angular/material';
import { Component, OnInit, ViewChild, Injectable, Type } from '@angular/core';


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

  }



}
