import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/task_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/tasks_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/task_page.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/tasks_page.dart';
import 'package:projetofinal_mobile/src/domain/use_case/get_answers_use_Case.dart';
import 'package:projetofinal_mobile/src/domain/use_case/get_tasks_use_case.dart';
import 'package:projetofinal_mobile/src/service/local/shared_preferences_service.dart';
import 'package:projetofinal_mobile/src/service/remote/auth/auth_service.dart';
import 'package:projetofinal_mobile/src/service/remote/tasks/tasks_service.dart';

class TasksModule extends Module {
  @override
  final List<Bind> binds = [
    Bind.lazySingleton((i) => SharedPreferencesService()),
    Bind.lazySingleton((i) => AuthService()),
    Bind.lazySingleton((i) => TasksService(i.get<AuthService>())),
    Bind.lazySingleton((i) => GetTasksUseCase()),
    Bind.lazySingleton((i) => GetAnswersUseCase()),
    Bind.lazySingleton(
      (i) => TasksBloc(getTasksUseCase: i.get<GetTasksUseCase>()),
    ),
    Bind.lazySingleton(
      (i) => TaskBloc(getAnswersUseCase: i.get<GetAnswersUseCase>()),
    ),
  ];

  @override
  final List<ModularRoute> routes = [
    ChildRoute(
      Modular.initialRoute,
      child: (context, args) => const TasksPage(),
    ),
    ChildRoute(
      TaskPage.route,
      child: (context, args) => TaskPage(task: args.data),
    ),
  ];
}
