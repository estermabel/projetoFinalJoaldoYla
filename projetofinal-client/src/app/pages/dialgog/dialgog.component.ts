import { TarefaDTO } from 'src/app/model/DTO/tarefaDTO';
import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { CasoTeste } from 'src/app/model/casoTeste';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material/dialog';
@Component({
  selector: 'app-dialgog',
  templateUrl: './dialgog.component.html',
  styleUrls: ['./dialgog.component.css']
})
export class DialgogComponent{
  teste: CasoTeste
  status: String;
  comparacoes = [
    {value: 0, viewValue: "Igual"},
    {value: 1, viewValue: "Igual ignorando case sensitive"},
    {value: 2, viewValue: "Contém"},
  ]

  constructor(
    public dialogRef: MatDialogRef<DialgogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: TarefaDTO,
    @Inject(SESSION_STORAGE) private storage: StorageService
  ) {
    this.teste = new CasoTesteDTO();
    this.status = "cancelar"
  }

  cancelar() {
    this.status = "cancelar"
    this.storage.set("status", this.status);
    this.storage.remove("teste");
    this.dialogRef.close();
  }

  cadastrarCasoTeste(){
    this.status = "cadastrar"
    this.storage.set("status", this.status);
    this.storage.set("teste", this.teste);
    this.dialogRef.close();
  }

}
