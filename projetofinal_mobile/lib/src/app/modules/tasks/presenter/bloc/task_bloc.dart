import 'dart:async';

import 'package:projetofinal_mobile/src/components/config/safe_event.dart';
import 'package:projetofinal_mobile/src/core/interfaces/safe_bloc.dart';
import 'package:projetofinal_mobile/src/domain/entity/answer_entity.dart';
import 'package:projetofinal_mobile/src/domain/entity/task_entity.dart';
import 'package:projetofinal_mobile/src/domain/use_case/get_answers_use_Case.dart';

class TaskBloc extends SafeBloC {
  final GetAnswersUseCase getAnswersUseCase;

  late StreamController<SafeEvent<List<AnswerEntity>>> getAnswersController;

  TaskBloc({
    required this.getAnswersUseCase,
  }) {
    init();
  }

  @override
  Future<void> init() async {
    getAnswersController = StreamController.broadcast();
  }

  Future<void> getAnswers({required TaskEntity? task}) async {
    try {
      getAnswersController.sink.add(SafeEvent.load());
      List<AnswerEntity> answers = await getAnswersUseCase(taskId: task?.id);
      getAnswersController.sink.add(SafeEvent.done(answers));
    } catch (e) {
      getAnswersController.sink.addError(e.toString());
    }
  }

  @override
  Future<void> dispose() async {}
}
