export class CasoTesteDTO{
  id: number;
  nomeTeste: string;
  entrada: string;
  saida: string;
  comparacao: number;

  constructor() {
    this.id = 0;
    this.nomeTeste= '';
    this.entrada=  '';
    this.saida = '';
    this.comparacao = 0;
  }
}
