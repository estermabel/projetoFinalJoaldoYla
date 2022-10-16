package br.com.ucsal.projetofinal.testcode;

import java.util.Arrays;
import java.util.Optional;

public enum ExceptionEnum {

    ARRAY ("java.lang.ArithmeticException", "operação aritmetica inválida "),
    DIVISAO_ZERO("java.lang.ArrayIndexOutOfBoundsException", "Index do array inválido"),
    CONVERTER_INT("java.lang.NumberFormatException", "variavel do tipo numérico tenta receber tipo de dado inválido");

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
