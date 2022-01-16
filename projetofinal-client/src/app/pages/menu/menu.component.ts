import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {
  }

  listarUsuarios(){

      this.router.navigate(["usuarios"])
  }

  listarTarefas(){

    this.router.navigate(["tarefas"])
  }

  cadastrarUsuarios(){
    this.router.navigate(["cadastrarUsuario"])
  }
}
