package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.concurrent.BlockingQueue;

public class CodeRunnable implements Runnable {

    private InputStreamReader streamReader;
    private BlockingQueue<String> queue;

    public CodeRunnable(InputStreamReader isr, BlockingQueue<String> queue) {
        this.streamReader = isr;
        this.queue = queue;
    }

    @Override
    public void run() {
        char[] charBuffer = new char[128];
        int length;
        try {
            while ((length = streamReader.read(charBuffer)) > 0) {
                String iterationResult = String.valueOf(Arrays.copyOfRange(charBuffer, 0, length));
                iterationResult = iterationResult.replace("\n", "").replace("\r", "");
                String res = "Rezultat koda:" + iterationResult + "x";
                queue.add(res); // !!!
            }
        } catch (IOException e) {
            if (!"stream closed".equalsIgnoreCase(e.getMessage())) {
                e.printStackTrace(); // todo: log IO exception
            }
        }
    }
}
