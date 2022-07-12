export class TesteDTO{
  execute: boolean;
  resultado: boolean;
  exception: String;
  saidaEsperada: string;
  saidaObtida: string;

  constructor(){
    this.execute = false;
    this.resultado = false;
    this.exception = "";
    this.saidaEsperada = "";
    this.saidaObtida = "";
  }
}
