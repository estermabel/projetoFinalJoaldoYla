import 'package:projetofinal_mobile/src/service/model/answer_model.dart';
import 'package:projetofinal_mobile/src/service/model/perfil_model.dart';
import 'package:projetofinal_mobile/src/service/model/task_model.dart';
import 'package:projetofinal_mobile/src/service/model/teste_model.dart';
import 'package:projetofinal_mobile/src/service/model/usuario_model.dart';

class ResponseGetAnswerByTaskId extends Answer {
  ResponseGetAnswerByTaskId({
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

  factory ResponseGetAnswerByTaskId.fromJson(dynamic json) {
    return ResponseGetAnswerByTaskId(
      id: json["id"],
      code: json["codigo"],
      sendDate: json["dataEnvio"],
      user: ResponseGetAnswerByTaskIdUsuario.fromJson(json["usuario"]),
      task: ResponseGetAnswerByTaskIdTarefa.fromJson(json["tarefa"]),
    );
  }
}

class ResponseGetAnswerByTaskIdUsuario extends Usuario {
  ResponseGetAnswerByTaskIdUsuario({
    int? id,
    String? name,
    bool? isActive,
    String? createdAt,
    String? updatedAt,
    List<ResponseGetAnswerByTaskIdPerfil>? profiles,
  }) : super(
          id: id,
          name: name,
          isActive: isActive,
          createdAt: createdAt,
          updatedAt: updatedAt,
          profiles: profiles,
        );

  factory ResponseGetAnswerByTaskIdUsuario.fromJson(dynamic json) {
    return ResponseGetAnswerByTaskIdUsuario(
      id: json['id'],
      name: json['nome'],
      isActive: json['flagAtivo'],
      createdAt: json['dataCriacao'],
      updatedAt: json['dataUltimoAcesso'],
      profiles: (json['perfil'] as List)
          .map((i) => ResponseGetAnswerByTaskIdPerfil.fromJson(i))
          .toList(),
    );
  }
}

class ResponseGetAnswerByTaskIdPerfil extends Perfil {
  ResponseGetAnswerByTaskIdPerfil({
    int? id,
    String? name,
    String? authority,
  }) : super(
          id: id,
          name: name,
          authority: authority,
        );

  factory ResponseGetAnswerByTaskIdPerfil.fromJson(dynamic json) {
    return ResponseGetAnswerByTaskIdPerfil(
      id: json['id'],
      name: json['nome'],
      authority: json['authority'],
    );
  }
}

class ResponseGetAnswerByTaskIdTarefa extends Task {
  ResponseGetAnswerByTaskIdTarefa({
    int? id,
    String? title,
    String? description,
    int? status,
    List<Teste>? tests,
    ResponseGetAnswerByTaskIdUsuario? user,
  }) : super(
          id: id,
          title: title,
          description: description,
          status: status,
          tests: tests,
          user: user,
        );

  factory ResponseGetAnswerByTaskIdTarefa.fromJson(dynamic json) {
    return ResponseGetAnswerByTaskIdTarefa(
      id: json['id'],
      title: json['titulo'],
      description: json['descricao'],
      status: json['status'],
      tests: json['testes'].map<Teste>((e) => Teste.fromJson(e)).toList(),
      user: ResponseGetAnswerByTaskIdUsuario.fromJson(json['usuario']),
    );
  }
}

class ResponseGetAnswerByTaskIdTestes extends Teste {
  ResponseGetAnswerByTaskIdTestes({
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

  factory ResponseGetAnswerByTaskIdTestes.fromJson(dynamic json) {
    return ResponseGetAnswerByTaskIdTestes(
      id: json['id'],
      name: json['nomeTeste'],
      input: json['entrada'],
      output: json['saida'],
      comparation: json['comparacao'],
      isShow: json['flagExibir'],
    );
  }
}
