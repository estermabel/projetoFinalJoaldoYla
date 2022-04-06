import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { SESSION_STORAGE, StorageService } from 'ngx-webstorage-service';
import { CasoTeste } from 'src/app/model/casoTeste';
import { CasoTesteDTO } from 'src/app/model/DTO/CasoTesteDTO';

@Component({
  selector: 'app-dialog-editar-caso-teste',
  templateUrl: './dialog-editar-caso-teste.component.html',
  styleUrls: ['./dialog-editar-caso-teste.component.css']
})
export class DialogEditarCasoTesteComponent implements OnInit {
  teste = new CasoTesteDTO();
  status: String;
  comparacoes = [
    {value: 0, viewValue: "Igual"},
    {value: 1, viewValue: "Igual ignorando case sensitive"},
    {value: 2, viewValue: "Contém"},
  ]
  constructor(public dialogRef: MatDialogRef<DialogEditarCasoTesteComponent>,
    @Inject(MAT_DIALOG_DATA) public data: CasoTeste,
    @Inject(SESSION_STORAGE) private storage: StorageService) { this.status = "cancelar" }

  ngOnInit(): void {
    this.teste = this.storage.get("testeEditar");
    console.log(this.teste)
  }


  cancelar() {
    this.status = "cancelar"
    this.storage.set("status", this.status);
    this.storage.remove("teste");
    this.dialogRef.close();
  }

  cadastrarCasoTeste(){
    this.storage.set("status", this.status);
    this.storage.set("teste", this.teste);
    this.dialogRef.close();
  }
}
