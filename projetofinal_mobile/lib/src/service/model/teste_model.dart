class Teste {
  int? id;
  String? name;
  String? input;
  String? output;
  int? comparation;
  bool? isShow;

  Teste({
    this.id,
    this.name,
    this.input,
    this.output,
    this.comparation,
    this.isShow,
  });

  factory Teste.fromJson(dynamic json) {
    return Teste(
      id: json['id'],
      name: json['nomeTeste'],
      input: json['entrada'],
      output: json['saida'],
      comparation: json['comparacao'],
      isShow: json['flagExibir'],
    );
  }
}
