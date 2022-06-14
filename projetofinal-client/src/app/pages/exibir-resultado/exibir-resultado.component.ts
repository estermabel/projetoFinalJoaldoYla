import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { CasoTeste } from './../../model/casoTeste';
import { RespostaService } from './../../service/resposta/resposta.service';
import { CasoTesteService } from './../../service/caso-teste/caso-teste.service';
import { ResultadoDTO } from './../../model/DTO/resultadoDTO';
import { RespostaDTO } from 'src/app/model/DTO/RespostaDTO';
import { Resposta } from './../../model/Resposta';
import { Resultado } from './../../model/resultado';
import { ResultadoService } from './../../service/resultado/resultado.service';
import { Component, Inject, OnInit } from '@angular/core';
import { BlockUI, NgBlockUI } from 'ng-block-ui';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { ActivatedRoute, Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material/table';

@Component({
  selector: 'app-exibir-resultado',
  templateUrl: './exibir-resultado.component.html',
  styleUrls: ['./exibir-resultado.component.css']
})
export class ExibirResultadoComponent implements OnInit {

  codigo: String = "";
  casoTeste: CasoTeste;

  resposta = new RespostaDTO();
  resultado = new ResultadoDTO();
  idResposta = 0
  @BlockUI() blockUI: NgBlockUI | undefined;

  resultados: Resultado[] = [];

  resultadosMock: ResultadoDTO[] = [
    {saidaObtida: "Hello Word!",
      resultado: true,
      resposta: new RespostaDTO(),
      casoTeste: new CasoTesteDTO(),
      respostaId: 1,
      casoTesteId: 1,
    },
    {saidaObtida: "Helloooooo Word!",
      resultado: false,
      resposta: new RespostaDTO(),
      casoTeste: new CasoTesteDTO(),
      respostaId: 1,
      casoTesteId: 1,
    }
  ]

  constructor(
    @Inject(SESSION_STORAGE) private storage: StorageService,
    private resultadoService: ResultadoService,
    private casoTesteService: CasoTesteService,
    private respostaService: RespostaService,
    private activatedRoute: ActivatedRoute
  ) {
    this.casoTeste = new CasoTesteDTO()
    this.blockUI?.start('Loading...'); // Start blocking

    setTimeout(() => {
      this.blockUI?.stop(); // Stop blocking
    }, 2000);
  }

  ngOnInit(): void {
    this.resposta = this.storage.get("respostaEnviada")
    this.blockUI?.start();
    //const par = this.activatedRoute.snapshot.paramMap.get('parametro');
    this.idResposta = this.resposta.id;
    console.log("id:"+ this.idResposta);
    //console.log(this.resultadosMock)

    this.resultadoService.findAll().subscribe((busca: Resultado[]) => {
      this.resultados = busca
      console.log(this.resultados);
    });

    this.blockUI?.stop()
  }

}
