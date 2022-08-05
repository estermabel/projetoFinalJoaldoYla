import { Teste } from './../teste';
import { RespostaDTO } from './RespostaDTO';
import { Resposta2 } from '../resposta';

export class ResultadoDTO{
  id: number;
  saidaObtida: string;
  // execption: string;
  create: boolean;
  compile: boolean;
  porcentagem: number;

  resposta: Resposta2;
  testes: Array<Teste>

  constructor(){
    this.id = 0
    this.saidaObtida = "";
    // this.execption = "";
    this.create = false;
    this.compile = false;
    this.porcentagem = 0;
    this.resposta = new RespostaDTO();
    this.testes = new Array<Teste>()
  }
}
