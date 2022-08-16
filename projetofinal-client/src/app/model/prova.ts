import { Tarefa } from "./tarefa"

export interface Prova{
  id: number,
  nome: string,
  tarefas: Array<number>,
  dataEntrega: Date,
}
