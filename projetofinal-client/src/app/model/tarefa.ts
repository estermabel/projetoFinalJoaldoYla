import { CasoTeste } from './casoTeste';
export interface Tarefa{
  id: number
  titulo: string,
  descricao: string,
  dataEntrega: Date,
  status: number,
  testes : Array<CasoTeste>
}
