package br.com.ucsal.projetofinal.testcode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TestResult {

    private String output;
    private Exception exception;
    private Boolean create;
    private Boolean compile;
    private List<Test> test = new ArrayList<>();

    @Data
    public static class Test {
        private Boolean execute;
        private Boolean test;
        private Exception exception;
        private String output;
    }
}
