package test;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class MainEngine {

	private static int[] numbers = {0, 1, 2, 3};

	public static void main(String[] args) {
//			Runtime runTime = Runtime.getRuntime();
//			Process compile = runTime.exec("javac -classpath C:\\Projects\\multiplayer-engine TestCode.java");
//			compile.waitFor(); // compiling doesn't execute instantly
//			Process process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine TestCode");
//			Process process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine\\out\\production\\multiplayer-engine test.TestCode");
//			InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
//			InputStreamReader errorReader = new InputStreamReader(process.getErrorStream());
//			OutputStreamWriter outputWriter = new OutputStreamWriter(process.getOutputStream());

//			Process processP2 = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine\\out\\production\\multiplayer-engine test.Uvecavac");
//			InputStreamReader inputReaderP2 = new InputStreamReader(processP2.getInputStream());
//			OutputStreamWriter outputWriterP2 = new OutputStreamWriter(processP2.getOutputStream());

            Player player1 = new Player("TestCode");
            Player player2 = new Player("Uvecavac");

			/*BlockingQueue<String> starterQueue = new ArrayBlockingQueue<>(3);
			BlockingQueue<String> resultQueue = new ArrayBlockingQueue<>(3);
			BlockingQueue<String> communicationQueue = new ArrayBlockingQueue<>(3);

			TimeoutRunnable timeoutRunnable = new TimeoutRunnable(communicationQueue, starterQueue, resultQueue);
			Thread timeoutThread = new Thread(timeoutRunnable);
			timeoutThread.start();

			CodeRunnable codeRunnable = new CodeRunnable(inputReader, communicationQueue);
			Thread codeExecutionThread = new Thread(codeRunnable);
			codeExecutionThread.start();*/


//			BlockingQueue<String> starterQueueP2 = new ArrayBlockingQueue<>(3);
//			BlockingQueue<String> resultQueueP2 = new ArrayBlockingQueue<>(3);
//			BlockingQueue<String> communicationQueueP2 = new ArrayBlockingQueue<>(3);

//			TimeoutRunnable timeoutRunnableP2 = new TimeoutRunnable(communicationQueueP2, starterQueueP2, resultQueueP2);
//			Thread timeoutThreadP2 = new Thread(timeoutRunnableP2);
//			timeoutThreadP2.start();

//			CodeRunnable codeRunnableP2 = new CodeRunnable(inputReaderP2, communicationQueueP2);
//			Thread codeExecutionThreadP2 = new Thread(codeRunnableP2);
//			codeExecutionThreadP2.start();


//			char[] charBuffer = new char[1024];
			for(int number : numbers) {
			    player1.send(number);
			    player2.send(number);
//				outputWriter.write(number + "\n");
//				outputWriter.flush();
//				outputWriterP2.write(number + "\n");
//				outputWriterP2.flush();
//				starterQueue.put("GO");
//				starterQueueP2.put("GO");
				System.out.println("P1: " + player1.getResponse());
				System.out.println("P2: " + player2.getResponse());
			}
			player1.stop();
			player2.stop();
//			process.destroy();
//			starterQueue.put("END");
//			starterQueueP2.put("END");
//			inputReader.close();
//			inputReaderP2.close();
//			Thread.sleep(2000);
//			timeoutThread.interrupt();
//			codeExecutionThread.interrupt();

	}
}