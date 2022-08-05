
import { Tarefa } from './tarefa';
import { Usuario } from './usuario';

export interface Resposta2{
  id: number,
  codigo: string,
  dataEnvio: Date,
  usuario: Usuario,
  tarefa: Tarefa,
  //resultado: Resultado,
  usuarioId: number,
  tarefaId: number,
  //resultadoId: number

}
