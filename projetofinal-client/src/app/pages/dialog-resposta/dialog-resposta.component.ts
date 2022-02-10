import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { Usuario } from 'src/app/model/usuario';
import { RespostaService } from './../../service/resposta/resposta.service';
import { RespostaDTO } from './../../model/DTO/RespostaDTO';
import { Resposta } from './../../model/Resposta';
import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';


@Component({
  selector: 'app-dialog-resposta',
  templateUrl: './dialog-resposta.component.html',
  styleUrls: ['./dialog-resposta.component.css']
})
export class DialogRespostaComponent implements OnInit {
  codigo: string = "";

  constructor(private respostaService: RespostaService,
    private usuarioService: UsuarioService,
    private tarefaService: TarefaService,
    private router: Router,
    @Inject(SESSION_STORAGE) private storage: StorageService,) { }

    formCadastrarResposta = new FormGroup({
      codigo: new FormControl(''),
    });

  usuario = new UsuarioDTO();
  resposta = new RespostaDTO();
  tarefa = new TarefaDTO();
  linguagem: string = "java";
  text : string = "public class Main {\npublic static void main(String[] args) {\n\n\n}";

  ngOnInit(): void {
    this.tarefa = this.storage.get("tarefa");
    this.tarefaService.findOne(this.tarefa.id).subscribe((data) => {
      this.tarefa = data;
      console.log(this.tarefa);
    });

    // DEPOIS SUBSTITUIR PELO USUARIO LOGADO
    this.usuarioService.findOne(1).subscribe((data) => {
      this.usuario = data;
      console.log(this.tarefa);
    });
  }

  EnviarResposta(){
    this.resposta.codigo = this.codigo
    this.resposta.usuarioId = this.usuario.id;
    this.resposta.tarefaId = this.tarefa.id;
    this.resposta.dataEnvio = new Date();
    this.respostaService.save(this.resposta).subscribe(data =>{
      console.log("cadastrado com sucesso", data);
    }, (error) =>{
      console.log(error.error);
    }
    )

  }

  onChange(code: any){
    //console.log("new code", code);
    this.codigo = code;
  }
}

