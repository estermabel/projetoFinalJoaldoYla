import { CasoTesteDTO } from './../../model/DTO/CasoTesteDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { CasoTeste } from 'src/app/model/casoTeste';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
@Component({
  selector: 'app-dialgog',
  templateUrl: './dialgog.component.html',
  styleUrls: ['./dialgog.component.css']
})
export class DialgogComponent{
  teste: CasoTeste

  constructor(
    public dialogRef: MatDialogRef<DialgogComponent>,

    @Inject(SESSION_STORAGE) private storage: StorageService
  ) {
    this.teste = new CasoTesteDTO();
  }

  cancelar(): void {
    this.storage.remove("teste");
    this.dialogRef.close();
  }

  cadastrarCasoTeste(){
    this.storage.set("teste", this.teste);
    this.dialogRef.close();
  }

}
