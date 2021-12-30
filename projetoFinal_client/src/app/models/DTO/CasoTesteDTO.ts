export class CasoTesteDTO{
  nomeTeste: string;
  entrada: string;
  saida: string;
  comparacao: number;

  constructor() {
    this.nomeTeste= '';
    this.entrada=  '';
    this.saida = '';
    this.comparacao = 0;
  }
}
