import 'package:projetofinal_mobile/src/domain/entity/task_entity.dart';
import 'package:projetofinal_mobile/src/domain/entity/user_entity.dart';
import 'package:projetofinal_mobile/src/service/model/answer_model.dart';

class AnswerEntity {
  int? id;
  String? code;
  String? sendDate;
  int? userId;
  int? taskId;
  UserEntity? user;
  TaskEntity? task;

  AnswerEntity({
    this.id,
    this.code,
    this.sendDate,
    this.user,
    this.task,
    this.taskId,
    this.userId,
  });

  factory AnswerEntity.toEntity(Answer? response) {
    return AnswerEntity(
      id: response?.id,
      code: response?.code,
      sendDate: response?.sendDate,
      taskId: response?.taskId,
      userId: response?.userId,
      user: UserEntity.toEntity(response?.user),
      task: TaskEntity.toEntity(response?.task),
    );
  }
}
