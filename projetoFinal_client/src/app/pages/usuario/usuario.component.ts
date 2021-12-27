
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild,  } from '@angular/core';
import { MatPaginator, MatSort, MatTableDataSource} from '@angular/material' ;
import { UsuarioService } from 'src/app/services/usuario/usuario.service';
import { Usuario } from 'src/app/models/usuario';


@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  dataSource = new MatTableDataSource<Usuario>();
  displayedColumns = [
    'nome',
    'login',
    'perfil'
  ];


  title = 'projetoFinal_client';



  constructor(private usuarioService: UsuarioService) {}


  ngOnInit() {

    this.buscarUsuarios();
  }


  buscarUsuarios(){
    this.usuarioService.findAll().subscribe(data => {
      this.dataSource.data = data;
      MatTableDataSource
      console.log(this.dataSource.data);
    });
  }

}
