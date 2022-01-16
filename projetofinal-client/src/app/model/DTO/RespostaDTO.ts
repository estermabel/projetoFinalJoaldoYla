import { TarefaDTO } from './tarefaDTO';
import { Tarefa } from "../tarefa";
import { Usuario } from "../usuario";
import { UsuarioDTO } from "./usuarioDTO";




export class RespostaDTO{
  id: number;
  codigo: string;
  dataEnvio: Date;
  usuario: Usuario;
  tarefa: Tarefa;

  constructor(){
    this.id = 0,
    this.codigo = "",
    this.dataEnvio = new Date(),
    this.usuario = new UsuarioDTO(),
    this.tarefa = new TarefaDTO()
  }
}
