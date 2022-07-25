import { Resultado } from './resultado';
import { Tarefa } from './tarefa';
import { Usuario } from './usuario';
export interface Resposta{
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
