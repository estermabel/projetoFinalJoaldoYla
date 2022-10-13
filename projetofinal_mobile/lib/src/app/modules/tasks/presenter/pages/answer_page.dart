import 'package:code_text_field/code_text_field.dart';
import 'package:flutter/material.dart';
import 'package:flutter_modular/flutter_modular.dart';
import 'package:projetofinal_mobile/generated/l10n.dart';
import 'package:projetofinal_mobile/src/app/modules/tasks/presenter/bloc/answer_bloc.dart';
import 'package:projetofinal_mobile/src/components/config/safe_event.dart';
import 'package:projetofinal_mobile/src/components/config/safe_layout.dart';
import 'package:projetofinal_mobile/src/components/style/colors/safe_colors.dart';
import 'package:projetofinal_mobile/src/components/style/text/text_styles.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';
import 'package:projetofinal_mobile/src/core/util/safe_log_util.dart';
import 'package:projetofinal_mobile/src/domain/entity/answer_entity.dart';
// ignore: depend_on_referenced_packages
import 'package:flutter_highlight/themes/monokai-sublime.dart';
// ignore: depend_on_referenced_packages
import 'package:highlight/languages/java.dart';
import 'package:projetofinal_mobile/src/domain/entity/result_entity.dart';
import 'package:projetofinal_mobile/src/domain/entity/test_entity.dart';

class AnswerPage extends StatefulWidget {
  static const route = '/answer';
  final AnswerEntity answer;
  const AnswerPage({super.key, required this.answer});

  @override
  State<AnswerPage> createState() => _AnswerPageState();
}

class _AnswerPageState extends ModularState<AnswerPage, AnswerBloc> {
  final _scaffoldKey = GlobalKey<ScaffoldState>();
  CodeController? _codeController;

  @override
  void initState() {
    super.initState();
    SafeLogUtil.instance.route(Modular.to.path);
    controller.getResult(widget.answer.id);
    _codeController = CodeController(
      text: widget.answer.code ?? StringConstants.empty,
      language: java,
      theme: monokaiSublimeTheme,
    );
  }

  @override
  void dispose() {
    _codeController?.dispose();
    controller.dispose();
    super.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      key: _scaffoldKey,
      appBar: AppBar(
        title: Text(
          widget.answer.user?.name ?? StringConstants.empty,
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
              CodeField(
                controller: _codeController!,
                enabled: false,
              ),
              const SizedBox(height: 20),
              StreamBuilder<SafeEvent<ResultEntity>>(
                stream: controller.getResultController.stream,
                builder: (context, snapshot) {
                  final result = snapshot.data?.data;
                  return SafeLayout(
                    snapshot: snapshot,
                    context: context,
                    onDone: () {},
                    onCompleted: ResultWidget(result: result),
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

class ResultWidget extends StatelessWidget {
  final ResultEntity? result;
  const ResultWidget({
    super.key,
    required this.result,
  });

  Color get color {
    if (result?.percentage == 0 && (result?.percentage ?? 0) < 20) {
      return SafeColors.statusColors.error;
    } else if (result?.percentage == 100) {
      return SafeColors.statusColors.success;
    } else {
      return SafeColors.statusColors.alert;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Container(
          height: MediaQuery.of(context).size.height * 0.08,
          margin: const EdgeInsets.only(bottom: 20),
          padding: const EdgeInsets.symmetric(horizontal: 20),
          decoration: BoxDecoration(
            color: color.withOpacity(0.1),
            border: Border.all(color: color),
            borderRadius: BorderRadius.circular(10),
          ),
          child: Row(
            children: [
              Text(
                '${S.current.textResult}${StringConstants.colon}',
                style: TextStyles.subtitle1().copyWith(
                  color: color,
                  fontWeight: FontWeight.bold,
                ),
                overflow: TextOverflow.ellipsis,
              ),
              const SizedBox(width: 10),
              Text(
                '${result?.percentage}${StringConstants.percent}',
                style: TextStyles.bodyText1().copyWith(
                  color: color,
                  fontWeight: FontWeight.w600,
                ),
              ),
            ],
          ),
        ),
        Text(
          S.current.textTest,
          style: TextStyles.headline2(
            color: SafeColors.generalColors.secondary,
          ),
        ),
        const SizedBox(height: 10),
        ...List.generate(
          result?.tests?.length ?? 0,
          (index) => TestWidget(
            index: index,
            test: result?.tests?[index],
          ),
        ),
      ],
    );
  }
}

class TestWidget extends StatelessWidget {
  final TestEntity? test;
  final int index;
  const TestWidget({
    super.key,
    required this.test,
    required this.index,
  });

  Color get color {
    if (test?.isCorrect ?? false) {
      return SafeColors.statusColors.success;
    } else if (test?.isCorrect == false) {
      return SafeColors.statusColors.error;
    } else {
      return SafeColors.statusColors.alert;
    }
  }

  IconData get icon {
    if (test?.isCorrect ?? false) {
      return Icons.check_circle_outline_outlined;
    } else if (test?.isCorrect == false) {
      return Icons.cancel_outlined;
    } else {
      return Icons.warning_amber_rounded;
    }
  }

  @override
  Widget build(BuildContext context) {
    return Container(
      height: MediaQuery.of(context).size.height * 0.08,
      margin: const EdgeInsets.only(bottom: 20),
      padding: const EdgeInsets.symmetric(horizontal: 20),
      decoration: BoxDecoration(
        color: color.withOpacity(0.1),
        border: Border.all(color: color),
        borderRadius: BorderRadius.circular(10),
      ),
      child: Row(
        children: [
          Icon(
            icon,
            color: color,
          ),
          const SizedBox(width: 10),
          Text(
            test?.name ?? '${S.current.textTest} ${index + 1}',
            style: TextStyles.subtitle1().copyWith(
              color: color,
              fontWeight: FontWeight.bold,
            ),
            overflow: TextOverflow.ellipsis,
          ),
        ],
      ),
    );
  }
}
