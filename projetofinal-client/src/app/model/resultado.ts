import { Teste } from './teste';
import { CasoTeste } from 'src/app/model/casoTeste';
import { Resposta } from './resposta';


export interface Resultado{
  id: number;
  saidaObtida: string;
  // execption: string;
  create: boolean;
  compile: boolean;
  porcentagem: number;

  resposta: Resposta;
  testes: Array<Teste>
}
