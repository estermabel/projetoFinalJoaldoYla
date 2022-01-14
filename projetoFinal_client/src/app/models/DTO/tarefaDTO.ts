import { CasoTeste } from './../casoTeste';
export class TarefaDTO{
  id: number
  titulo: string;
  descricao: string;
  dataEntrega: Date;
  testes= new Array<CasoTeste>()

  constructor() {
    this.id = 0,
    this.titulo = '',
    this.descricao = ''
    this.dataEntrega = new Date()
  }
}
