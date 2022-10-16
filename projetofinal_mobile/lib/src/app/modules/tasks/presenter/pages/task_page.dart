import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/task_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/answer_page.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/tasks_page.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/widgets/answer_widget.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/widgets/sextion_title_widget.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/widgets/text_body_section_widget.dart';
import 'package:projetofinal_mobile/src/components/config/safe_event.dart';
import 'package:projetofinal_mobile/src/components/config/safe_layout.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';
import 'package:projetofinal_mobile/generated/l10n.dart';
import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';
import 'package:projetofinal_mobile/src/domain/entity/answer_entity.dart';
import 'package:projetofinal_mobile/src/domain/entity/task_entity.dart';
import 'package:projetofinal_mobile/src/domain/use_case/do_register_admin_use_case.dart';

class TaskPage extends StatefulWidget {
  static const route = '/task';
  final TaskEntity task;

  const TaskPage({
    super.key,
    required this.task,
  });

  @override
  State<TaskPage> createState() => _TaskPageState();
}

class _TaskPageState extends ModularState<TaskPage, TaskBloc> {
  final _scaffoldKey = GlobalKey<ScaffoldState>();

  @override
  void initState() {
    super.initState();
    SafeLogUtil.instance.route(Modular.to.path);
    getAllAnswers();
  }

  @override
  void dispose() {
    controller.dispose();
    super.dispose();
  }

  Future<void> getAllAnswers() async {
    await controller.getMyAnswers(widget.task.id);
    await controller.getAnswers(task: widget.task);
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: Text(
          widget.task.title ?? StringConstants.empty,
          overflow: TextOverflow.ellipsis,
        ),
        centerTitle: false,
      ),
      body: SafeArea(
        child: SingleChildScrollView(
          padding: const EdgeInsets.symmetric(
            horizontal: 20,
            vertical: 20,
          ),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              SectionTitleWidget(title: S.current.textTitle),
              const SizedBox(height: 10),
              TextBodySectionWidget(text: widget.task.title),
              const SizedBox(height: 20),
              SectionTitleWidget(title: S.current.textDescription),
              const SizedBox(height: 10),
              TextBodySectionWidget(text: widget.task.description),
              const SizedBox(height: 20),
              StreamBuilder<SafeEvent<List<AnswerEntity>>>(
                  stream: controller.getAnswersController.stream,
                  builder: (context, snapshot) {
                    final answers = snapshot.data?.data;
                    return Visibility(
                      visible: controller.userRole != RoleEnum.student,
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          SectionTitleWidget(title: S.current.textAnswers),
                          const SizedBox(height: 15),
                          SafeLayout(
                            snapshot: snapshot,
                            context: context,
                            onDone: () {},
                            showErrorDialog: controller.isShowErrorDialog,
                            onError: TextBodySectionWidget(
                              text: snapshot.error.toString(),
                            ),
                            onCompleted: Column(
                              children: List.generate(
                                answers?.length ?? 0,
                                (index) => AnswerWidget(
                                  answer: answers?[index],
                                  onTap: () async => Modular.to.pushNamed(
                                    TasksPage.route + AnswerPage.route,
                                    arguments: answers?[index],
                                  ),
                                ),
                              ),
                            ),
                          ).build
                        ],
                      ),
                    );
                  }),
              const SizedBox(height: 20),
              StreamBuilder<SafeEvent<List<AnswerEntity>>>(
                  stream: controller.getMyAnswersController.stream,
                  builder: (context, snapshot) {
                    final answers = snapshot.data?.data;
                    return Visibility(
                      child: Column(
                        crossAxisAlignment: CrossAxisAlignment.start,
                        children: [
                          SectionTitleWidget(title: S.current.textMyAnswers),
                          const SizedBox(height: 15),
                          SafeLayout(
                            snapshot: snapshot,
                            context: context,
                            onDone: () {},
                            showErrorDialog: controller.isShowErrorDialog,
                            onError: TextBodySectionWidget(
                              text: snapshot.error.toString(),
                            ),
                            onCompleted: Column(
                              children: List.generate(
                                answers?.length ?? 0,
                                (index) => AnswerWidget(
                                  answer: answers?[index],
                                  isAt: true,
                                  onTap: () async => Modular.to.pushNamed(
                                    TasksPage.route + AnswerPage.route,
                                    arguments: answers?[index],
                                  ),
                                ),
                              ),
                            ),
                          ).build
                        ],
                      ),
                    );
                  }),
            ],
          ),
        ),
      ),
    );
  }
}
