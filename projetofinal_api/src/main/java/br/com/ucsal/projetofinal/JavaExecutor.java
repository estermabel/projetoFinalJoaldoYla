package br.com.ucsal.projetofinal;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JavaExecutor {

    private String saida;
    private boolean teste;

    public String getSaida() {
        return saida;
    }

    public boolean getTeste() {
        return teste;
    }

    public void start(String codigo) {

        //Cria arquivo
        File file = new File("./Main.java");

        //Adiciona o codigo ao arquivo
        FileWriter fileWritter = null;
        try {
            fileWritter = new FileWriter(file.getAbsolutePath(), false);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedWriter bufferWritter = new BufferedWriter(fileWritter)) {
            bufferWritter.write(codigo);
            bufferWritter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //compila
        compile("./", "javac", "Main.java");

        //executa
        execute("./", "java", "Main");

        //OBS: O ultimo parametro, "saida:" é a saida esperada do teste
        executeWithTest("./", "java", "Main", "\n", "Olá Mundo");
    }

    public static String compile(String path, String comando, String arquivo) {

        var sb = new StringBuilder("");
        var builder = new ProcessBuilder(comando, arquivo);

        builder.directory(new File(path));

        Process process;
        try {
            process = builder.start();
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            var error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            while ((line = error.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
            sb.append(e.getMessage() + "\n");
        }

        return sb.toString();
    }

    public static String execute(String path, String comando, String arquivo) {

        var sb = new StringBuilder();

        var builder = new ProcessBuilder(comando, arquivo);

        builder.directory(new File(path));
        Process process;

        try {
            process = builder.start();
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            var error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line = "";

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            while ((line = error.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    private void executeWithTest(String path, String comando, String arquivo, String entrada, String saida) {

        var builder = new ProcessBuilder(comando, arquivo);
        builder.directory(new File(path));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
            var stdin = process.getOutputStream();
            var stdout = process.getInputStream();

            var writer = new BufferedWriter(new OutputStreamWriter(stdin));
            var isFirstLine = true;
            var st = new StringTokenizer(entrada, "\n");

            while (st.hasMoreTokens()) {
                String line = st.nextToken();
                if (isFirstLine) {
                    writer.write(line);
                    isFirstLine = false;
                } else {
                    writer.write("\n" + line);
                }
            }

            writer.flush();
            writer.close();

            var scanner = new Scanner(stdout);
            var resposta = new StringBuilder();
            isFirstLine = true;
            while (scanner.hasNextLine()) {
                if (isFirstLine) {
                    resposta.append(scanner.nextLine());
                    isFirstLine = false;
                } else {
                    resposta.append("\n" + scanner.nextLine());
                }
            }

            scanner.close();

            StringBuilder output = new StringBuilder();
            output.append(resposta);

            boolean isCorrect = resposta.toString().equals(saida);

            this.saida = resposta.toString();
            this.teste = isCorrect;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
