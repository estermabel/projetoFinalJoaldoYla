

import { Component, Input, OnInit, ViewChild,  } from '@angular/core';
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
  @Input()  usuarios: any[] = [];
  displayedColumns = [
    'nome',
    'login',
    'data ultimo acesso',
    'data cadastro',
    'perfil'
  ];


  title = 'projetoFinal_client';



  constructor(private usuarioService: UsuarioService) {}


  ngOnInit() {

    this.buscarUsuarios();
  }


  buscarUsuarios(){
    this.usuarioService.findAll().subscribe((data: any[]) => {
      this.usuarios = data;
      console.log(this.usuarios);
    });
  }

}
