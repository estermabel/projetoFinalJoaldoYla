import { CasoTeste } from './../casoTeste';
export class TarefaDTO{
  id: number
  titulo: string;
  descricao: string;
  dataEntrega= new Date();
  testes:  Array<CasoTeste>;

  constructor() {
    this.id = 0,
    this.titulo = '',
    this.descricao = '',
    this.dataEntrega = new Date(),
    this.testes = new Array<CasoTeste>()

  }
}
