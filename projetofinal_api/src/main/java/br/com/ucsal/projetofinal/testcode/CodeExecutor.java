package br.com.ucsal.projetofinal.testcode;

import lombok.Builder;
import lombok.Getter;

import java.io.*;
import java.util.*;

@Getter
@Builder
public class CodeExecutor {

    private String codigo;

    private String folder;

    private String filename;

    private String compileCommand;

    private String executeCommand;

    private Object inputs[];

    private Object outputs[];

    @Builder.Default
    private List<String> details = new ArrayList<>();



    public TestResult runTests()  {
        TestResult result = new TestResult();
        File file = null;
        try {
            file = create();
            result.setCreate(true);
        } catch (IOException e) {
            e.printStackTrace();
            result.setException(e);
            result.setCreate(false);
            return result;
        }

        String exit = null;
        try {
            exit = compile(file);
            if (exit.length() != 0 ){
                result.setOutput(exit);
                result.setCompile(false);
                return result;
            }else {
                result.setCompile(true);
            }
        } catch (IOException e) {
            result.setException(e);
            result.setCompile(false);
            return result;
        }

        if (inputs == null || inputs.length == 0){
            inputs = new String[]{""};
        }

        if (outputs == null || outputs.length == 0){
            outputs = new String[]{""};
        }

        for (int i = 0; i < outputs.length; i++) {
            System.out.println("***********");
            System.out.println(inputs[i].toString());
            System.out.println("+++++++++");
            System.out.println(outputs[i].toString());
            TestResult.Test test = runTest(inputs[i].toString(), outputs[i].toString(), file);
            result.getTest().add(test);
        }

        return result;

    }


    public TestResult.Test runTest(String input, String output, File file) {
        TestResult.Test test = new TestResult.Test();
        try {
            Boolean isOK = execute(file,input, output );
            test.setExecute(true);
            if (isOK){
                test.setTest(true);
            }else{
                test.setTest(false);
            }
            test.setOutput(Arrays.toString(details.toArray()));
        } catch (IOException e) {
            test.setException(e);
            test.setExecute(false);
            return test;
        }
        return test;
    }


    private File create() throws IOException {
        //Cria arquivo
        if(filename == null || filename.isBlank()) {
            filename = "Main.java";
        }
        File directory = new File(folder);
        if(!directory.exists()){
            directory.mkdir();
        }
        File file = new File(directory.getAbsolutePath()+"/"+filename);
        //Adiciona o codigo ao arquivo
        FileWriter fileWritter = new FileWriter(file.getAbsolutePath(), false);
        BufferedWriter bufferWritter = new BufferedWriter(fileWritter);
        bufferWritter.write(codigo);
        bufferWritter.flush();

        return file;
    }

    private String compile(File file) throws IOException {
        var sb = new StringBuilder("");
        var builder = new ProcessBuilder(compileCommand, file.getName());
        builder.directory(file.getParentFile());
        Process process;
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
        return sb.toString();
    }

    private boolean execute(File file, String input, String output) throws IOException {
        if(input == null || input.isBlank() ){
            input ="";
        }
        if(output == null || output.isBlank() ){
            output ="";
        }
        String classfile = file.getName().substring(0,file.getName().lastIndexOf("."));
        var builder = new ProcessBuilder(executeCommand, classfile);
        builder.directory(file.getParentFile());
        builder.redirectErrorStream(true);
        Process process = null;
        process = builder.start();
        var stdin = process.getOutputStream();
        var stdout = process.getInputStream();
        var writer = new BufferedWriter(new OutputStreamWriter(stdin));
        var isFirstLine = true;
        var st = new StringTokenizer(input, "\n");

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
//            StringBuilder console = new StringBuilder();
//            console.append('\n' );
//            console.append("expected:");
//            console.append(output);
//            console.append('\n' );
//            console.append("Result:" );
//            console.append(resposta);
//            console.append('\n' );
            details.add(output);

            if (output == null || output.isEmpty() ) {
                return true;
            }

        return resposta.toString().equals(output);
    }
}
