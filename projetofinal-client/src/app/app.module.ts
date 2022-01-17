import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialExampleModule } from 'src/material.module';
import { ListarUsuariosComponent } from './pages/listar-usuarios/listar-usuarios.component';
import { MenuComponent } from './pages/menu/menu.component';
import { HttpClientModule } from '@angular/common/http';
import { ListarTarefasComponent } from './pages/listar-tarefas/listar-tarefas.component';
import { CadastrarTarefaComponent } from './pages/cadastrar-tarefa/cadastrar-tarefa.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CadastrarUsuarioComponent } from './pages/cadastrar-usuario/cadastrar-usuario.component';
import { registerLocaleData } from '@angular/common';
import {MatNativeDateModule} from '@angular/material/core';
import localePt from '@angular/common/locales/pt';
import { DialgogComponent } from './pages/dialgog/dialgog.component';

registerLocaleData(localePt, 'pt');
@NgModule({
  declarations: [
    AppComponent,
    ListarUsuariosComponent,
    MenuComponent,
    ListarTarefasComponent,
    CadastrarTarefaComponent,
    CadastrarUsuarioComponent,
    DialgogComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    MaterialExampleModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    { provide: LOCALE_ID, useValue: 'pt' },
    {
      provide: DEFAULT_CURRENCY_CODE,
      useValue: 'BRL',
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
