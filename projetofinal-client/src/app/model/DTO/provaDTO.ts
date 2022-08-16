import { Tarefa } from 'src/app/model/tarefa';

export class ProvaDTO{
  id: number;
  nome: string;
  tarefas: Array<number>;
  dataEntrega: Date;

  constructor(){
    this.id = 0,
    this.nome ="",
    this.tarefas = new Array<number>(),
    this.dataEntrega = new Date()
  }
}
