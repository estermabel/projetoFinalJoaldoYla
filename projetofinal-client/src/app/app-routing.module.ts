import { ListarResultadosComponent } from './pages/listar-resultados/listar-resultados.component';
import { CadastrarUsuarioComponent } from './pages/cadastrar-usuario/cadastrar-usuario.component';
import { CadastrarTarefaComponent } from './pages/cadastrar-tarefa/cadastrar-tarefa.component';
import { ListarTarefasComponent } from './pages/listar-tarefas/listar-tarefas.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListarUsuariosComponent } from './pages/listar-usuarios/listar-usuarios.component';
import { CadastrarRespostaComponent } from './pages/cadastrar-resposta/cadastrar-resposta.component';

const routes: Routes = [
  { path: 'usuarios', component: ListarUsuariosComponent },
  { path: 'tarefas', component: ListarTarefasComponent },
  { path: 'cadastrarTarefa', component: CadastrarTarefaComponent },
  { path: 'cadastrarUsuario', component: CadastrarUsuarioComponent },
  { path: 'cadastrarResposta', component: CadastrarRespostaComponent },
  { path: 'resultados', component: ListarResultadosComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
