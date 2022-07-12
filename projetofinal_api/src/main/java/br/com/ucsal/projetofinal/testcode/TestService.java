package br.com.ucsal.projetofinal.testcode;

import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

@Log
@Service
public class TestService {

    public TestResult executetest(String codigo, String filename, String folder, Object[] inputs, Object[] outputs) {

        CodeExecutor codeExecutor = CodeExecutor.builder().codigo(codigo).
                compileCommand("javac").
                executeCommand("java").
                filename(filename).
                folder(folder)
                .inputs(inputs)
                .output(outputs)
                .build();

        return codeExecutor.runTests();
    }
}
