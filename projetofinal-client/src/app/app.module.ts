import { httpInterceptorProviders } from './account/';
import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AceEditorModule } from 'ng2-ace-editor';
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
import { DialogCasoTesteComponent } from './pages/dialog-caso-teste/dialog-caso-teste.component';
import { CadastrarRespostaComponent } from './pages/cadastrar-resposta/cadastrar-resposta.component';
import { DialogRespostaComponent } from './pages/dialog-resposta/dialog-resposta.component';
import { CommonsModule } from './commons/commons.module';
import { ExibirResultadoComponent } from './pages/exibir-resultado/exibir-resultado.component';
import { ListarResultadosComponent } from './pages/listar-resultados/listar-resultados.component';
import { BlockUIModule } from 'ng-block-ui';
import { DialogEditarCasoTesteComponent } from './pages/dialog-editar-caso-teste/dialog-editar-caso-teste.component';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './layout/home/home.component';
import { AuthenticationComponent } from './layout/authentication/authentication.component';


registerLocaleData(localePt, 'pt');
@NgModule({
  declarations: [
    AppComponent,
    ListarUsuariosComponent,
    MenuComponent,
    ListarTarefasComponent,
    CadastrarTarefaComponent,
    CadastrarUsuarioComponent,
    DialogCasoTesteComponent,
    CadastrarRespostaComponent,
    DialogRespostaComponent,
    ExibirResultadoComponent,
    ListarResultadosComponent,
    DialogEditarCasoTesteComponent,
    LoginComponent,
    HomeComponent,
    AuthenticationComponent
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    FormsModule,
    AppRoutingModule,
    MaterialExampleModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    AceEditorModule,
    BrowserAnimationsModule,
    BlockUIModule.forRoot(),
    CommonsModule
  ],
  providers: [
    httpInterceptorProviders,
    { provide: LOCALE_ID, useValue: 'pt' },
    {
      provide: DEFAULT_CURRENCY_CODE,
      useValue: 'BRL',
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
