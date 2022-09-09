
import { Tarefa } from './tarefa';
import { Usuario } from './usuario';

export interface Resposta2{
  id: number,
  codigo: string,
  dataEnvio: Date,
  usuario: Usuario,
  tarefa: Tarefa,
  usuarioId: number,
  tarefaId: number,


  porcentagemAcerto: number,
}
