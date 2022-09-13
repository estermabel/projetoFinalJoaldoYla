import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/tasks_module.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/tasks_page.dart';
import 'package:projetofinal_mobile/src/app/modules/navigation/bloc/navigation_bloc.dart';

import 'presenter/pages/navigation_page.dart';

class NavigationModule extends Module {
  @override
  final List<Bind> binds = [
    Bind.lazySingleton((i) => NavigationBloc()),
  ];

  @override
  final List<ModularRoute> routes = [
    ChildRoute(
      Modular.initialRoute,
      child: (context, args) => const NavigationPage(),
      children: [
        ModuleRoute(TasksPage.route, module: TasksModule()),
        // ModuleRoute(Quizzes.route, module: QuizzesModule()),
        // ModuleRoute(ProfilePage.route, module: ProfileModule()),
      ],
    ),
  ];
}
