import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/tasks_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/tasks_page.dart';

class TasksModule extends Module {
  @override
  final List<Bind> binds = [
    // Bind.lazySingleton((i) => HomeService(i.get<AuthService>())),
    Bind.lazySingleton((i) => TasksBloc()),
  ];

  @override
  final List<ModularRoute> routes = [
    ChildRoute(
      Modular.initialRoute,
      child: (context, args) => const TasksPage(),
    ),
  ];
}
