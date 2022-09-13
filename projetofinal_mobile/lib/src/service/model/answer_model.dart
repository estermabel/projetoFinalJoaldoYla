import 'package:projetofinal_mobile/src/service/model/task_model.dart';
import 'package:projetofinal_mobile/src/service/model/usuario_model.dart';

class Answer {
  int? id;
  String? code;
  String? sendDate;
  Usuario? user;
  Task? task;

  Answer({
    this.id,
    this.code,
    this.sendDate,
    this.user,
    this.task,
  });
}
