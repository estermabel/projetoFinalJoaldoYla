import { TarefaDTO } from './tarefaDTO';
import { Tarefa } from "../tarefa";
import { Usuario } from "../usuario";
import { UsuarioDTO } from "./usuarioDTO";
import { ResultadoDTO } from './resultadoDTO';
import { Resultado } from '../resultado';




export class RespostaDTO{
  id: number;
  codigo: string;
  dataEnvio: Date;
  usuario: Usuario;
  tarefa: Tarefa;
  usuarioId: number;
  tarefaId: number;
  resultadoId: number;

  porcentagemAcerto: number;


  constructor(){
    this.id= 0,
    this.codigo = "",
    this.dataEnvio = new Date(),
    this.usuario = new UsuarioDTO(),
    this.tarefa = new TarefaDTO(),

    this.usuarioId = 0,
    this.tarefaId = 0,
    this.resultadoId =0

    this.porcentagemAcerto = 0
  }
}
