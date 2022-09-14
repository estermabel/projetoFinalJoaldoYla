import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_answer_by_task_id.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_tasks.dart';

abstract class ITasksService {
  Future<List<ResponseGetTasks>> getTasks();
  Future<List<ResponseGetAnswerByTaskId>> getAnswersByTaskId(int taskId);
}
