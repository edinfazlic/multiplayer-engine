package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Player {

    Process process;
    private OutputStreamWriter outputWriter;
    private BlockingQueue<String> starterQueue;
    private BlockingQueue<String> resultQueue;
    InputStreamReader inputReader;
    Thread timeoutThread;
    Thread codeExecutionThread;

    public Player(String className) {
        Runtime runTime = Runtime.getRuntime();

        try {
            process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine\\out\\production\\multiplayer-engine test." + className);
        } catch (IOException e) {
            e.printStackTrace();
        }
        inputReader = new InputStreamReader(process.getInputStream());
//			InputStreamReader errorReader = new InputStreamReader(process.getErrorStream());
        outputWriter = new OutputStreamWriter(process.getOutputStream());



        starterQueue = new ArrayBlockingQueue<>(3);
        resultQueue = new ArrayBlockingQueue<>(3);
        BlockingQueue<String> communicationQueue = new ArrayBlockingQueue<>(3);

        TimeoutRunnable timeoutRunnable = new TimeoutRunnable(communicationQueue, starterQueue, resultQueue);
        timeoutThread = new Thread(timeoutRunnable);
        timeoutThread.start();

        CodeRunnable codeRunnable = new CodeRunnable(inputReader, communicationQueue);
        codeExecutionThread = new Thread(codeRunnable);
        codeExecutionThread.start();
    }

    public void send(int number) {
        try {
            outputWriter.write(number + "\n");
            outputWriter.flush();
            starterQueue.put("GO");
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getResponse() {
        String result;
        try {
            result = resultQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public void stop() {
        process.destroy();
        starterQueue.add("END");
        try {
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeoutThread.interrupt();
        codeExecutionThread.interrupt();
    }
}
