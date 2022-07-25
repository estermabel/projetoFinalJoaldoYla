import * as ace from "ace-builds";
import { ResultadoRequestDTO } from './../../model/DTO/resultadoRequestDTO';
import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { CasoTeste } from './../../model/casoTeste';
import { RespostaService } from './../../service/resposta/resposta.service';
import { CasoTesteService } from './../../service/caso-teste/caso-teste.service';
import { ResultadoDTO } from './../../model/DTO/resultadoDTO';
import { RespostaDTO } from 'src/app/model/DTO/RespostaDTO';
import { Resposta } from './../../model/resposta';
import { Resultado } from './../../model/resultado';
import { ResultadoService } from './../../service/resultado/resultado.service';
import { AfterViewInit, Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';


@Component({
  selector: 'app-exibir-resultado',
  templateUrl: './exibir-resultado.component.html',
  styleUrls: ['./exibir-resultado.component.css']
})
export class ExibirResultadoComponent implements OnInit, AfterViewInit {

  codigo: String = "";
  casoTeste: CasoTeste;

  resposta = new RespostaDTO();
  resultado = new ResultadoDTO();
  idResposta = 0

  @BlockUI() blockUI: NgBlockUI | undefined;
  @ViewChild("editor") private editor!: ElementRef<HTMLElement>;

  porcentagem: number = 0;

  constructor(
    @Inject(SESSION_STORAGE) private storage: StorageService,
    private resultadoService: ResultadoService,
    private casoTesteService: CasoTesteService,
    private respostaService: RespostaService,
    private activatedRoute: ActivatedRoute
  ) {
    this.casoTeste = new CasoTesteDTO();
  }

  ngOnInit(): void {
    this.resposta = this.storage.get("respostaEnviada")

    //const par = this.activatedRoute.snapshot.paramMap.get('parametro');
    this.idResposta = this.resposta.id;
    console.log("id:"+ this.idResposta);

    this.resultadoService.listarPorResposta(this.idResposta).subscribe((busca: Resultado) => {
      this.resultado = busca;
      console.log(this.resultado);
    });

  }

  ngAfterViewInit(): void {
    //ace.config.set("fontSize", "14px");
    ace.config.set('basePath', "https://ace.c9.io/build/src-noconflict/");
    const aceEditor = ace.edit(this.editor.nativeElement);


    aceEditor.setFontSize("14px");
    aceEditor.session.setValue(this.resposta.codigo);
    aceEditor.setTheme("ace/theme/one_dark");
    aceEditor.session.setMode("ace/mode/java");
    aceEditor.setReadOnly(true);
  }



}
