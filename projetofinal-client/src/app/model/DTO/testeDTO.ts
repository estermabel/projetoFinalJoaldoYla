export class TesteDTO{
  execute: boolean;
  resultado: boolean;
  exception: String;
  entrada: String;
  saidaEsperada: string;
  saidaObtida: string;

  constructor(){
    this.execute = false;
    this.resultado = false;
    this.exception = "";
    this.entrada = "";
    this.saidaEsperada = "";
    this.saidaObtida = "";
  }
}
