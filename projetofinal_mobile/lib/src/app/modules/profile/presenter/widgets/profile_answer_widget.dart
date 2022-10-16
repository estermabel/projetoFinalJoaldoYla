import 'package:flutter/material.dart';
import 'package:projetofinal_mobile/src/components/style/colors/safe_colors.dart';
import 'package:projetofinal_mobile/src/components/style/text/text_styles.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';
import 'package:projetofinal_mobile/src/core/util/formatter_util.dart';
import 'package:projetofinal_mobile/src/domain/entity/answer_entity.dart';

class ProfileAnswerWidget extends StatelessWidget {
  final AnswerEntity? answer;
  final bool isAt;
  final Future<void> Function()? onTap;
  const ProfileAnswerWidget({
    super.key,
    required this.answer,
    this.isAt = false,
    this.onTap,
  });

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: onTap ?? () {},
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
              (answer?.task?.title ?? StringConstants.empty),
              style: TextStyles.subtitle1().copyWith(
                color: SafeColors.generalColors.secondary,
                fontWeight: FontWeight.bold,
              ),
              overflow: TextOverflow.ellipsis,
            ),
            const Spacer(),
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
          ],
        ),
      ),
    );
  }
}
