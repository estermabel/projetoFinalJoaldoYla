import { Resposta } from './../Resposta';

export class ResultadoDTO{
  id: number;
  saidaObtida: String;
  resultado: boolean;
  respostaId: number;
  casoTesteId: number;

  constructor(){
    this.id = 0;
    this.saidaObtida = "";
    this.resultado = true;
    this.respostaId = 0;
    this.casoTesteId = 0;
  }
}
