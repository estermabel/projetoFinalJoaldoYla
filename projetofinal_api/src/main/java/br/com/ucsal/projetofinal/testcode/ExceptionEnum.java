package br.com.ucsal.projetofinal.testcode;

import java.util.Arrays;
import java.util.Optional;

public enum ExceptionEnum {

    ARITHMETIC ("java.lang.ArithmeticException", "Você pode ter tentado realizar uma divisão por zero.\n\nTalvez o resultado de sua operação não possa ser representado como um número de ponto flutuante."),
    ARRAY_INDEX("java.lang.ArrayIndexOutOfBoundsException", "Você tentou acessar um índice menor que zero, ou igual ou maior que o tamanho total da sua matriz."),
    NULL_POINTER("java.lang.NullPointerException", "Talvez você esteja tentando acessar um índice com valor nulo na sua matriz.\n\nTentando passar parâmetros nulo para o seu método.\n\nTentou acessar um campo de uma instância cuja referência seja null.\n\nVocê pode ter tentando obter uma referência a um método de um objeto nulo."),
//    ILEGAL_STATE("java.lang.IllegalStateException", ""),
//    CLASS_CAST("java.lang.ClassCastException", ""),
    STRING_INDEX_BOUND("java.lang.StringIndexOutOfBoundsException","Talvez o índice que você está tentando acessar não está no intervalo da string, ou seja, negativo ou maior que o último índice da string."),
    ILEGAL_ARGUMENT("java.lang.IllegalArgumentException", "Os argumentos passados para o seu método estão fora do intervalo.\n\nTalvez o formato do valor que está tentando passar seja inválido.\n\nVocê pode está tentando receber um objeto com valor nulo."),
    NUMBER_FORMAT("java.lang.NumberFormatException", "Variavel do tipo numérico tenta receber tipo de dado inválido");

    private final String saida;
    private final String saidaSimplificada;


    ExceptionEnum(String saida, String saidaSimplificada) {
        this.saida = saida;
        this.saidaSimplificada = saidaSimplificada;
    }

    public String getSaida() {
        return saida;
    }

    public String getSaidaSimplificada() {
        return saidaSimplificada;
    }

    public static Optional<ExceptionEnum> getSaidaSimplificadaBySaida(String value){
        final Optional<ExceptionEnum> first = Arrays.stream(ExceptionEnum.values()).filter(excep -> value.contains(excep.getSaida())).findFirst();
        return first;
    }
}
