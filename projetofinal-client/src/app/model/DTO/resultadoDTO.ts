import { CasoTesteDTO } from './CasoTesteDTO';
import { RespostaDTO } from './RespostaDTO';
import { Resposta } from './../Resposta';
import { CasoTeste } from '../casoTeste';

export class ResultadoDTO{
  saidaObtida: string;
  resultado: boolean;

  resposta: Resposta;
  casoTeste: CasoTeste;

  respostaId: number;
  casoTesteId: number;

  constructor(){
    this.saidaObtida = "";
    this.resultado = false;
    this.resposta = new RespostaDTO();
    this.casoTeste = new CasoTesteDTO();
    this.respostaId = 0;
    this.casoTesteId = 0;
  }
}
