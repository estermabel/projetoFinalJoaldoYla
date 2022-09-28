import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/task_bloc.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/answer_page.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/pages/tasks_page.dart';
import 'package:projetofinal_mobile/src/components/config/safe_event.dart';
import 'package:projetofinal_mobile/src/components/config/safe_layout.dart';
import 'package:projetofinal_mobile/src/components/style/colors/safe_colors.dart';
import 'package:projetofinal_mobile/src/components/style/text/text_styles.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';
import 'package:projetofinal_mobile/generated/l10n.dart';
import 'package:projetofinal_mobile/src/core/util/formatter_util.dart';
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
                      //TODO checar melhor regra para deixar visivel
                      // visible: controller.userRole == RoleEnum.student,

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

class AnswerWidget extends StatelessWidget {
  final AnswerEntity? answer;
  final bool isAt;
  const AnswerWidget({
    super.key,
    required this.answer,
    this.isAt = false,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () async => Modular.to.pushNamed(
        TasksPage.route + AnswerPage.route,
        arguments: answer,
      ),
      child: Container(
        height: MediaQuery.of(context).size.height * 0.08,
        margin: const EdgeInsets.only(bottom: 20),
        padding: const EdgeInsets.symmetric(horizontal: 20),
        decoration: BoxDecoration(
          color: SafeColors.generalColors.secondary.withOpacity(0.1),
          border: Border.all(color: SafeColors.generalColors.secondary),
          borderRadius: BorderRadius.circular(10),
        ),
        child: Row(
          children: [
            Text(
              (answer?.user?.name ?? StringConstants.empty),
              style: TextStyles.subtitle1().copyWith(
                color: SafeColors.generalColors.secondary,
                fontWeight: FontWeight.bold,
              ),
              overflow: TextOverflow.ellipsis,
            ),
            const SizedBox(width: 10),
            Text(
              isAt
                  ? '(${FormatterUtil.dateFromAPIv2(
                      answer?.sendDate ?? StringConstants.empty,
                    )})'
                  : '(${FormatterUtil.dateFromAPI(
                      answer?.sendDate ?? StringConstants.empty,
                    )})',
              style: TextStyles.bodyText2().copyWith(
                color: SafeColors.generalColors.secondary,
                fontWeight: FontWeight.w500,
              ),
            ),
            const Spacer(),
            Icon(
              Icons.arrow_forward_ios,
              color: SafeColors.generalColors.secondary,
              size: 20,
            ),
          ],
        ),
      ),
    );
  }
}

class TextBodySectionWidget extends StatelessWidget {
  final String? text;
  const TextBodySectionWidget({
    Key? key,
    required this.text,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      text ?? StringConstants.empty,
      style: TextStyles.bodyText1(
        color: SafeColors.generalColors.secondary,
      ),
    );
  }
}

class SectionTitleWidget extends StatelessWidget {
  final String title;
  const SectionTitleWidget({
    Key? key,
    required this.title,
  }) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Text(
      title,
      style: TextStyles.headline2(
        color: SafeColors.generalColors.secondary,
      ),
    );
  }
}
