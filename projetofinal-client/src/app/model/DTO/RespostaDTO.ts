import { TarefaDTO } from './tarefaDTO';
import { Tarefa } from "../tarefa";
import { Usuario } from "../usuario";
import { UsuarioDTO } from "./usuarioDTO";




export class RespostaDTO{

  codigo: string;
  dataEnvio: Date;
  usuarioId: number;
  tarefaId: number;

  constructor(){

    this.codigo = "",
    this.dataEnvio = new Date(),
    this.usuarioId = 0,
    this.tarefaId = 0
  }
}
