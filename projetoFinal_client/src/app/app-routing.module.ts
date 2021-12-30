import { CadastrarTarefaComponent } from './pages/cadastrar-tarefa/cadastrar-tarefa.component';

import { CasoTeste } from './models/casoTeste';
import { TarefaComponent } from './pages/listar-tarefa/tarefa.component';
import { UsuarioComponent } from './pages/listar-usuario/usuario.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CasoTesteComponent } from './pages/listar-caso-teste/caso-teste.component';



const routes: Routes = [
  { path: 'usuarios', component: UsuarioComponent },
  { path: 'tarefas', component: TarefaComponent },
  { path: 'casoTeste', component: CasoTesteComponent },
  { path: 'cadastrarTarefa', component: CadastrarTarefaComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
