import { ProvaService } from './../../service/prova/prova.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import { Prova } from 'src/app/model/prova';

@Component({
  selector: 'app-listar-provas',
  templateUrl: './listar-provas.component.html',
  styleUrls: ['./listar-provas.component.css']
})
export class ListarProvasComponent implements OnInit {

  constructor(private provaService: ProvaService) { }

  provas = new MatTableDataSource<Prova>();
  displayedColumns = ['nome'];

  ngOnInit(): void {
    this.provaService.findAll().subscribe((data: any[]) => {
      this.provas.data = data;
      //console.log(this.usuarios);
    });
  }

}
