class Teste {
  int? id;
  String? name;
  String? input;
  String? output;
  int? comparation;
  bool? isShow;
  bool? executed;
  bool? isCorrect;
  dynamic exception;
  String? expectedOutput;
  String? obtainedOutput;

  Teste({
    this.id,
    this.name,
    this.input,
    this.output,
    this.comparation,
    this.isShow,
    this.executed,
    this.isCorrect,
    this.exception,
    this.expectedOutput,
    this.obtainedOutput,
  });

  factory Teste.fromJson(dynamic json) {
    return Teste(
      id: json['id'],
      name: json['nomeTeste'],
      input: json['entrada'],
      output: json['saida'],
      comparation: json['comparacao'],
      isShow: json['flagExibir'],
      executed: json['execute'],
      isCorrect: json['resultadoFinal'],
      exception: json['exception'],
      expectedOutput: json['saidaEsperada'],
      obtainedOutput: json['saidaObtida'],
    );
  }
}
