package br.com.ucsal.projetofinal;

import java.io.*;
import java.util.Scanner;
import java.util.StringTokenizer;

public class JavaExecutor {

    public static void main(String[] args) {


        //Codigo
        String codigo = "public class AloMundo { " +
                "public static void main(String[] args){" +
                "   System.out.println(\"Olá Mundo\");" +
                "}" +
                "}";

        //Cria arquivo
        File file = new File("./AloMundo.java");

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
        System.out.println(compile("./","javac", "AloMundo.java" ));

        //executa
        execute("./","java","AloMundo");

        executeWithTest("./","java","AloMundo","\n","Olá Mundo");
    }


//    public void static executeCode(String conteudo) {
//
//        result = new Resultado();
//
//        setFileJava(DirectoryUtil.criarArquivoComExtensao(DIRETORIO_NOME_ARQUIVO, Constante.ARQUIVO_TIPO_JAVA));
//
//        DirectoryUtil.adicionarConteudoArquivo(getFileJava(), conteudo);
//
//        // PASTA ATUAL
////		System.out.println(FileSystems.getDefault().getPath("").toAbsolutePath().toString());
//
//        output = new StringBuilder();
//
//        Optional<ArquivoMetrica> arqMetric;
//
//        try {
//            arqMetric = iniciarAnalise(getFileJava(), tarefa.getFiltros(), Constante.QTD_MIN_LINHAS_METODO_DEUS,
//                    Constante.QTD_MIN_LINHAS_CLASSE_DEUS);
//
//            if (arqMetric.isPresent())
//                result.getArquivosMetrica().add(arqMetric.get());
//
//        } catch (IOException e) {
//            LOGGER.catching(e.getCause());
//            // output.append(e.getMessage() + Constante.QUEBRA_LINHA);
//        }
//        acessarArquivosAnalisados();
//
//        ArquivoMetrica arquivoPrincipal = null;
//
//        arquivoPrincipal = obterArquivoPrincipal();
//
//        if (arquivoPrincipal != null) {
//            String nomeArquivo = arquivoPrincipal.obterNomeArquivo();
//
//            // First stage - Compile
//            output.append(compilar(DIRETORIO, "javac", nomeArquivo));
//
//            try {
//                if (tarefa.getTestes() != null && !tarefa.getTestes().isEmpty()) {
//
//                    for (Teste teste : tarefa.getTestes()) {
//
//                        // Second stage - Execute
//                        ResultadoTeste resultadoComTeste = executarComTeste(DIRETORIO, "java", nomeArquivo,
//                                teste.getEntradas(), teste.getSaidas());
//
//                        result.getMapResultTest().put(teste, resultadoComTeste);
//                        result.getSaidasObtidas().add(output.toString());
//                        output = new StringBuilder();
//                    }
//
//                } else {
//                    executarSemTeste(DIRETORIO, "java", nomeArquivo);
//                    result.getSaidasObtidas().add(output.toString());
//                    output = new StringBuilder();
//                }
//
//            } catch (IOException e) {
//                output.append(e.getMessage() + Constante.QUEBRA_LINHA);
//            }
//
//        } else {
//            LOGGER.warn("Não foi possível executar o código");
//            output = new StringBuilder();
//        }
//
//    }

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

            // int exitCode = process.waitFor();
            // System.out.println("\nExited with error code : " + exitCode);

        } catch (IOException e) {
            e.printStackTrace();
            sb.append(e.getMessage() + "\n");
        }

        return sb.toString();

    }


    public static void execute(String path, String comando, String arquivo)  {

        var sb = new StringBuilder();

        var builder = new ProcessBuilder(comando, arquivo);

        builder.directory(new File(path));
        Process process;

        try {
            process = builder.start();
            var reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            var error = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            String line ="";

            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }

            while ((line = error.readLine()) != null) {
                sb.append(line + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(sb.toString());

    }

    public static void executeWithTest(String path, String comando, String arquivo, String entrada, String saida) {

        var builder = new ProcessBuilder(comando, arquivo);
        builder.directory(new File(path));
        builder.redirectErrorStream(true);
        Process process = null;
        try {
            process = builder.start();
            var stdin = process.getOutputStream();
            var stdout = process.getInputStream();

//		var reader = new BufferedReader(new InputStreamReader(stdout));
//		var error = new BufferedReader(new InputStreamReader(process.getErrorStream()));

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
            output.append(resposta.toString());

            boolean isCorrect = resposta.toString().equals(saida);

            System.out.println(resposta);
            System.out.println(isCorrect);

        } catch (IOException e) {
            e.printStackTrace();
        }




    }

}
