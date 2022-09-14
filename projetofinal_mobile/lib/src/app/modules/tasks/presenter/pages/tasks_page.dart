import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/generated/l10n.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/tasks_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/task_page.dart';
import 'package:projetofinal_mobile/src/components/config/safe_event.dart';
import 'package:projetofinal_mobile/src/components/config/safe_layout.dart';
import 'package:projetofinal_mobile/src/components/style/colors/safe_colors.dart';
import 'package:projetofinal_mobile/src/components/style/text/text_styles.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';

import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';
import 'package:projetofinal_mobile/src/domain/entity/task_entity.dart';

class TasksPage extends StatefulWidget {
  static const route = '/tasks';
  const TasksPage({Key? key}) : super(key: key);

  @override
  State<TasksPage> createState() => _TasksPageState();
}

class _TasksPageState extends ModularState<TasksPage, TasksBloc> {
  final _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  void initState() {
    super.initState();
    SafeLogUtil.instance.route(Modular.to.path);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.symmetric(
            horizontal: 20,
            vertical: 20,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    S.current.textTasks,
                    style: TextStyles.headline1(
                      color: SafeColors.generalColors.secondary,
                    ),
                  ),
                  GestureDetector(
                    onTap: () => controller.getTasks(),
                    child: Icon(
                      Icons.refresh,
                      color: SafeColors.generalColors.secondary,
                      size: 30,
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 20),
              StreamBuilder<SafeEvent<List<TaskEntity>>>(
                stream: controller.getTasksController.stream,
                builder: (context, snapshot) {
                  final tasks = snapshot.data?.data;
                  return SafeLayout(
                    snapshot: snapshot,
                    context: context,
                    onDone: () {},
                    onCompleted: Column(
                      children: List.generate(
                        tasks?.length ?? 0,
                        (index) => TaskWidget(task: tasks?[index]),
                      ),
                    ),
                  ).build;
                },
              ),
            ],
          ),
        ),
      ),
    );
  }
}

class TaskWidget extends StatelessWidget {
  final TaskEntity? task;

  const TaskWidget({
    Key? key,
    required this.task,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () async => Modular.to.pushNamed(
        TasksPage.route + TaskPage.route,
        arguments: task,
      ),
      child: Container(
        height: MediaQuery.of(context).size.height * 0.2,
        margin: const EdgeInsets.only(bottom: 20),
        padding: const EdgeInsets.all(20),
        decoration: BoxDecoration(
          color: SafeColors.generalColors.secondary.withOpacity(0.1),
          border: Border.all(color: SafeColors.generalColors.secondary),
          borderRadius: BorderRadius.circular(10),
        ),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            Text(
              task?.title ?? StringConstants.hyphen,
              style: TextStyles.headline3(
                color: SafeColors.generalColors.secondary,
              ),
              overflow: TextOverflow.ellipsis,
              maxLines: 2,
            ),
            const SizedBox(height: 10),
            Text(
              task?.description ?? StringConstants.hyphen,
              style: TextStyles.bodyText1(
                color: SafeColors.generalColors.secondary,
              ),
              overflow: TextOverflow.ellipsis,
              maxLines: 2,
            ),
          ],
        ),
      ),
    );
  }
}
