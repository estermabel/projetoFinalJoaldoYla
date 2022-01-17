import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CasoTeste } from 'src/app/model/casoTeste';

@Component({
  selector: 'app-dialgog',
  templateUrl: './dialgog.component.html',
  styleUrls: ['./dialgog.component.css']
})
export class DialgogComponent{
  teste: CasoTeste

  constructor(
    public dialogRef: MatDialogRef<DialgogComponent>,
    private storage: Storage
  ) {
    this.teste = new CasoTesteDTO();
    this.storage = window.localStorage;
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  cadastrarCasoTeste(){
    console.log(this.teste)
    this.storage.setItem("teste", JSON.stringify(this.teste));
  }

}
