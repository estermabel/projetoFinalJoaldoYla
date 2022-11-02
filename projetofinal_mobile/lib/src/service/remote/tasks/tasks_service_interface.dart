import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_answer_by_task_id.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_answer_by_user_id.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_answer_result.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_tasks.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/response/response_get_tests_by_task_id.dart';

abstract class ITasksService {
  Future<List<ResponseGetTasks>> getTasks();
  Future<List<ResponseGetAnswerByTaskId>> getAnswersByTaskId(int taskId);
  Future<List<ResponseGetAnswerByUserId>> getAnswersByUserId(int userId);
  Future<ResponseGetAnswerResult> getAnswerResult(int id);
  Future<List<ResponseGetTestByTaskId>> getTestsByTaskId(int taskId);
}
