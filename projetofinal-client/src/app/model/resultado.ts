import { CasoTeste } from 'src/app/model/casoTeste';
import { Resposta } from './Resposta';

export interface Resultado{
  id: number;
  saidaObtida: string;
  resultado: boolean;

  resposta: Resposta;
  casoTeste: CasoTeste;

  respostaId: number;
  casoTesteId: number;
}
