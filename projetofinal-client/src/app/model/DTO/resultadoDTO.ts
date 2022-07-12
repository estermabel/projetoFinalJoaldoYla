import { Teste } from './../teste';
import { CasoTesteDTO } from './CasoTesteDTO';
import { RespostaDTO } from './RespostaDTO';
import { CasoTeste } from '../casoTeste';
import { Resposta } from '../resposta';

export class ResultadoDTO{
  saidaObtida: string;
  // execption: string;
  create: boolean;
  compile: boolean;
  porcentagem: number;

  resposta: Resposta;
  testes: Array<Teste>

  constructor(){
    this.saidaObtida = "";
    // this.execption = "";
    this.create = false;
    this.compile = false;
    this.porcentagem = 0;
    this.resposta = new RespostaDTO();
    this.testes = new Array<Teste>()
  }
}
