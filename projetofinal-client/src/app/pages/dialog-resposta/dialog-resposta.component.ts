import { MatDialogRef } from '@angular/material/dialog';
import * as ace from "ace-builds";
import { UsuarioService } from 'src/app/service/usuario/usuario.service';
import { TarefaService } from 'src/app/service/tarefa/tarefa.service';
import { UsuarioDTO } from 'src/app/model/DTO/usuarioDTO';
import { Usuario } from 'src/app/model/usuario';
import { RespostaService } from './../../service/resposta/resposta.service';
import { RespostaDTO } from './../../model/DTO/RespostaDTO';
import { Resposta } from './../../model/resposta';
import { AfterViewInit, Component, ElementRef, Inject, OnInit, ViewChild, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import { ResultadoDTO } from 'src/app/model/DTO/resultadoDTO';
import { ResultadoService } from 'src/app/service/resultado/resultado.service';
import { ResultadoRequestDTO } from 'src/app/model/DTO/resultadoRequestDTO';

@Component({
  selector: 'app-dialog-resposta',
  templateUrl: './dialog-resposta.component.html',
  styleUrls: ['./dialog-resposta.component.css']
})
export class DialogRespostaComponent implements OnInit, AfterViewInit {

  @ViewChild("editor") private editor!: ElementRef<HTMLElement>;

  codigo: string = "";
  idResposta: number = 0;
  constructor(private respostaService: RespostaService,
    private usuarioService: UsuarioService,
    private tarefaService: TarefaService,
    private router: Router,
    public dialogRef: MatDialogRef<DialogRespostaComponent>,
    @Inject(SESSION_STORAGE) private storage: StorageService,
    private resultadoService: ResultadoService,
    elementRef: ElementRef) { }

   /* formCadastrarResposta = new FormGroup({
      codigo: new FormControl(''),
    });*/

  usuario = new UsuarioDTO();
  resposta = new RespostaDTO();
  tarefa = new TarefaDTO();
  resultado = new ResultadoDTO();
  resultadoRequestDTO= new ResultadoRequestDTO()

  ngOnInit(): void {
    this.tarefa = this.storage.get("tarefa");
    this.tarefaService.findOne(this.tarefa.id).subscribe((data) => {
      this.tarefa = data;
      console.log(this.tarefa);
    });

    // DEPOIS SUBSTITUIR PELO USUARIO LOGADO
    this.usuarioService.findOne(1).subscribe((data) => {
      this.usuario = data;
      console.log(this.usuario);
    });
  }

  ngAfterViewInit(): void {
    //ace.config.set("fontSize", "14px");
    ace.config.set(
      "basePath",
      "https://unpkg.com/ace-builds@1.4.12/src-noconflict"
    );
    const aceEditor = ace.edit(this.editor.nativeElement);
    aceEditor.session.setValue("public class Main {\n\tpublic static void main(String[] args) {\n\n\n\t}\n}");
    aceEditor.setTheme("ace/theme/twilight");
    aceEditor.session.setMode("ace/mode/java");

    aceEditor.on("change", () => {
      //console.log(aceEditor.getValue());
      this.codigo = aceEditor.getValue();
    });
  }

  EnviarResposta(){
    this.resposta.codigo = this.codigo;
    this.resposta.usuarioId = this.usuario.id;
    this.resposta.tarefa = this.tarefa;
    this.resposta.tarefaId = this.tarefa.id;
    //this.resposta.dataEnvio = new Date();

    this.respostaService.save(this.resposta).subscribe( data =>{
      //this.executarCodigo(data.id)
      this.storage.set("respostaEnviada", data);
      console.log("cadastrado com sucesso", data);
    }, (error) =>{
      console.log(error.error);
    },
    () => {
      // this.resposta.tarefa.testes.forEach(casoTeste => {
      //   this.executarCodigo(casoTeste.id)
      // });

      this.dialogRef.close();
      this.router.navigate(["resultado"])
    }
    )


  }

  executarCodigo(id: number){
    let r = new RespostaDTO()
    r = this.storage.get("respostaEnviada")
    //this.storage.get("respostaEnviada")
    this.resultadoRequestDTO.casoTesteId = id;
    this.resultadoRequestDTO.respostaId = r.id;

    console.log(this.resultadoRequestDTO);
    this.resultadoService.save(this.resultadoRequestDTO).subscribe(data =>{
      console.log("resultado cadastrado com sucesso", data);
    }, (error) =>{
      console.log("erro ao cadastrar resultado ",error);
    },()=>
    this.router.navigate(["resultado"])
    )
  }
}

