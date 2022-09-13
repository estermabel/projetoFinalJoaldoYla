import 'package:projetofinal_mobile/src/service/model/teste_model.dart';

class TestEntity {
  int? id;
  String? name;
  String? input;
  String? output;
  int? comparation;
  bool? isShow;

  TestEntity({
    this.id,
    this.name,
    this.input,
    this.output,
    this.comparation,
    this.isShow,
  });

  factory TestEntity.toEntity(Teste? response) {
    return TestEntity(
      id: response?.id,
      name: response?.name,
      input: response?.input,
      output: response?.output,
      comparation: response?.comparation,
      isShow: response?.isShow,
    );
  }
}
