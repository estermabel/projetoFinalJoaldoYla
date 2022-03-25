package br.com.ucsal.projetofinal.teste;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TesteExecutarJava {
    private static final String PATH = "C:\\Users\\yla_m\\git\\TesteExecutarJava\\src\\";
    private static final String ARQUIVO = "Main.java";
    public static void main(String[] args) {
        String result = "";


        // javac C:\Users\yla_m\git\TesteExecutarJava\src\Main.java
        try {
            result = TesteExecutarJava.execCommand("cmd /C javac C:\\Users\\yla_m\\git\\TesteExecutarJava\\src\\Main.java");
            System.out.println("resultado: "+result);
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }

        try {
            result = TesteExecutarJava.execCommand("cmd /C java C:\\Users\\yla_m\\git\\TesteExecutarJava\\src\\Main");
            System.out.println("resultado: "+result);
        } catch (IOException e) {
            System.err.println(e.getMessage());;
        }


    }

    public synchronized static String execCommand(final String commandLine) throws IOException {

        boolean success = false;
        String result;

        Process p;
        BufferedReader input;
        StringBuffer cmdOut = new StringBuffer();
        String lineOut = null;
        int numberOfOutline = 0;

        try {

            p = Runtime.getRuntime().exec(commandLine);

            input = new BufferedReader(new InputStreamReader(p.getInputStream()));

            while ((lineOut = input.readLine()) != null) {
                if (numberOfOutline > 0) {
                    cmdOut.append("\n");
                }
                cmdOut.append(lineOut);
                numberOfOutline++;
            }
            System.out.println(cmdOut.toString());
            result = cmdOut.toString();

            success = true;

            input.close();

        } catch (IOException e) {
            result = String.format("Falha ao executar comando %s. Erro: %s", commandLine, e.toString());
        }

        // Se não executou com sucesso, lança a falha
        if (!success) {
            throw new IOException(result);
        }
        System.out.println(result);
        return result;

    }
}
