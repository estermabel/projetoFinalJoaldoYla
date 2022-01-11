import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { UsuarioComponent } from './pages/listar-usuario/usuario.component';
import { MatTableModule } from '@angular/material/table'
import { MatSortModule, MatPaginatorModule } from '@angular/material';
import { TarefaComponent } from './pages/listar-tarefa/tarefa.component';
import localePt from '@angular/common/locales/pt';
import { MatMenuModule} from '@angular/material/menu';
import { registerLocaleData } from '@angular/common';
import { CasoTesteComponent } from './pages/listar-caso-teste/caso-teste.component';
import { CadastrarTarefaComponent } from './pages/cadastrar-tarefa/cadastrar-tarefa.component';
import { MenuComponent } from './pages/menu/menu.component';
import { FormModalComponent } from './pages/form-modal/form-modal.component';
import { CadastrarUsuarioComponent } from './pages/cadastrar-usuario/cadastrar-usuario.component';

registerLocaleData(localePt, 'pt');

@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    TarefaComponent,
    CasoTesteComponent,
    CadastrarTarefaComponent,
    MenuComponent,
    FormModalComponent,
    CadastrarUsuarioComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatMenuModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule
  ],exports:[ MatTableModule ],
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
