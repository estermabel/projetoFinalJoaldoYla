
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { AccountService } from 'src/app/account/service/account.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(
    private router:Router,
    private accountService: AccountService
  ) { }
  usuario = new  UsuarioDTO();
  formLogin =  new FormGroup({
    login: new FormControl([Validators.required]),
    senha: new FormControl([Validators.required]),
  });

  ngOnInit(): void {
  }

  login(){
    this.router.navigate(['tarefas'])
  }
}
