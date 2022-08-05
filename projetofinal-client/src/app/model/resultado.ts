import { Teste } from './teste';
import { Resposta2 } from './resposta';


export interface Resultado{
  id: number;
  saidaObtida: string;
  // execption: string;
  create: boolean;
  compile: boolean;
  porcentagem: number;

  resposta: Resposta2;
  testes: Array<Teste>
}
