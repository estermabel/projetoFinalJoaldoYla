import 'package:projetofinal_mobile/generated/l10n.dart';
import 'package:projetofinal_mobile/src/core/constants/string_constants.dart';

/// A classe [FormatterUtil] é responsável por gerenciar os métodos de utilidades de formatação utilizados no projeto.
class FormatterUtil {
  ///Formata a data que vem da API.
  static String dateFromAPI(String date) {
    final dateSplit = date.split(StringConstants.t);
    final dateResult = dateSplit.first.substring(0, 10).split(
          StringConstants.hyphen,
        );

    return dateResult[2] +
        StringConstants.slash +
        dateResult[1] +
        StringConstants.slash +
        dateResult[0];
  }

  @Deprecated("Método está depreciado, utilize o método [dateFromAPI]")
  static String dateFromAPIv2(String date) {
    var dateSplit = date.split(StringConstants.at);
    var timeSplit = dateSplit[1].split(StringConstants.colon);
    var time = timeSplit[0] + StringConstants.colon + timeSplit[1];

    var split = dateSplit[0].split(StringConstants.hyphen);
    return '${split[0]}${StringConstants.slash}${split[1]}${StringConstants.slash}${split[2]} ${S.current.textAt} $time';
  }
}
