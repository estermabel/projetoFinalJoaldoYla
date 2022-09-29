import 'package:projetofinal_mobile/src/service/model/answer_model.dart';
import 'package:projetofinal_mobile/src/service/model/perfil_model.dart';
import 'package:projetofinal_mobile/src/service/model/task_model.dart';
import 'package:projetofinal_mobile/src/service/model/teste_model.dart';
import 'package:projetofinal_mobile/src/service/model/usuario_model.dart';

class ResponseGetAnswerByUserId extends Answer {
  ResponseGetAnswerByUserId({
    int? id,
    String? code,
    String? sendDate,
    Usuario? user,
    Task? task,
  }) : super(
          id: id,
          code: code,
          sendDate: sendDate,
          user: user,
          task: task,
        );

  factory ResponseGetAnswerByUserId.fromJson(dynamic json) {
    return ResponseGetAnswerByUserId(
      id: json["id"],
      code: json["codigo"],
      sendDate: json["dataEnvio"],
      user: ResponseGetAnswerByUserIdUsuario.fromJson(json["usuario"]),
      task: ResponseGetAnswerByUserIdTarefa.fromJson(json["tarefa"]),
    );
  }
}

class ResponseGetAnswerByUserIdUsuario extends Usuario {
  ResponseGetAnswerByUserIdUsuario({
    int? id,
    String? name,
    bool? isActive,
    String? createdAt,
    String? updatedAt,
    List<ResponseGetAnswerByUserIdPerfil>? profiles,
  }) : super(
          id: id,
          name: name,
          isActive: isActive,
          createdAt: createdAt,
          updatedAt: updatedAt,
          profiles: profiles,
        );

  factory ResponseGetAnswerByUserIdUsuario.fromJson(dynamic json) {
    return ResponseGetAnswerByUserIdUsuario(
      id: json['id'],
      name: json['nome'],
      isActive: json['flagAtivo'],
      createdAt: json['dataCriacao'],
      updatedAt: json['dataUltimoAcesso'],
      profiles: (json['perfil'] as List)
          .map((i) => ResponseGetAnswerByUserIdPerfil.fromJson(i))
          .toList(),
    );
  }
}

class ResponseGetAnswerByUserIdPerfil extends Perfil {
  ResponseGetAnswerByUserIdPerfil({
    int? id,
    String? name,
    String? authority,
  }) : super(
          id: id,
          name: name,
          authority: authority,
        );

  factory ResponseGetAnswerByUserIdPerfil.fromJson(dynamic json) {
    return ResponseGetAnswerByUserIdPerfil(
      id: json['id'],
      name: json['nome'],
      authority: json['authority'],
    );
  }
}

class ResponseGetAnswerByUserIdTarefa extends Task {
  ResponseGetAnswerByUserIdTarefa({
    int? id,
    String? title,
    String? description,
    int? status,
    List<Teste>? tests,
    ResponseGetAnswerByUserIdUsuario? user,
  }) : super(
          id: id,
          title: title,
          description: description,
          status: status,
          tests: tests,
          user: user,
        );

  factory ResponseGetAnswerByUserIdTarefa.fromJson(dynamic json) {
    return ResponseGetAnswerByUserIdTarefa(
      id: json['id'],
      title: json['titulo'],
      description: json['descricao'],
      status: json['status'],
      tests: json['testes'].map<Teste>((e) => Teste.fromJson(e)).toList(),
      user: ResponseGetAnswerByUserIdUsuario.fromJson(json['usuario']),
    );
  }
}

class ResponseGetAnswerByUserIdTestes extends Teste {
  ResponseGetAnswerByUserIdTestes({
    int? id,
    String? name,
    String? input,
    String? output,
    int? comparation,
    bool? isShow,
  }) : super(
          id: id,
          name: name,
          input: input,
          output: output,
          comparation: comparation,
          isShow: isShow,
        );

  factory ResponseGetAnswerByUserIdTestes.fromJson(dynamic json) {
    return ResponseGetAnswerByUserIdTestes(
      id: json['id'],
      name: json['nomeTeste'],
      input: json['entrada'],
      output: json['saida'],
      comparation: json['comparacao'],
      isShow: json['flagExibir'],
    );
  }
}
