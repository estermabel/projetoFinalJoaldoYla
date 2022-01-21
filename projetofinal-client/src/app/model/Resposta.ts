import { Tarefa } from './tarefa';
import { Usuario } from './usuario';
export interface Resposta{
  id: number,
  codigo: string,
  dataEnvio: Date,
  usuario: Usuario,
  tarefa: Tarefa

}
