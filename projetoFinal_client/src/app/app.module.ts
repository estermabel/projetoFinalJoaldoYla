import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import { UsuarioComponent } from './pages/usuario/usuario.component';
import { MatTableModule } from '@angular/material/table'
import { MatSortModule, MatPaginatorModule } from '@angular/material';
import { TarefaComponent } from './pages/tarefa/tarefa.component'

@NgModule({
  declarations: [
    AppComponent,
    UsuarioComponent,
    TarefaComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    MatTableModule,
    MatSortModule,
    MatPaginatorModule,
    FormsModule
  ],exports:[ MatTableModule ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
