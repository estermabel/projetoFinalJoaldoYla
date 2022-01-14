import { CasoTesteService } from './../../services/caso-teste/caso-teste.service';
import { CasoTeste } from './../../models/casoTeste';
import { Component, Input, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';


@Component({
  selector: 'app-caso-teste',
  templateUrl: './caso-teste.component.html',
  styleUrls: ['./caso-teste.component.css']
})
export class CasoTesteComponent implements OnInit {
  dataSource : CasoTeste[] = [];
  @Input()  casosTestes: any[] = [];
  displayedColumns = [
    'nomeTeste',
    'entrada',
    'saida',
    'comparacao'
  ];

  constructor(private casoTesteService: CasoTesteService) { }

  ngOnInit(): void {
    this.buscarCasosTeste()
  }

  buscarCasosTeste(){
    this.casoTesteService.findAll().subscribe((data: any[]) => {
      this.dataSource = data;
      this.casosTestes = data;
      console.log(this.casosTestes);
    });
  }
}
