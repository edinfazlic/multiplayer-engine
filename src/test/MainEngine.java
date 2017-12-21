package test;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MainEngine {

	private static int[] numbers = {1, 2, 3, 4};

	public static void main(String[] args) {
		try {
			Runtime runTime = Runtime.getRuntime();
//			Process compile = runTime.exec("javac -classpath C:\\Projects\\multiplayer-engine TestCode.java");
//			compile.waitFor(); // compiling doesn't execute instantly
//			Process process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine TestCode");
			Process process = runTime.exec("java -classpath C:\\Users\\Edin\\IdeaProjects\\multiplayer-engine\\out\\production\\multiplayer-engine test.TestCode");
			InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
//			InputStreamReader errorReader = new InputStreamReader(process.getErrorStream());
			OutputStreamWriter outputWriter = new OutputStreamWriter(process.getOutputStream());

			Process processP2 = runTime.exec("java -classpath C:\\Users\\Edin\\IdeaProjects\\multiplayer-engine\\out\\production\\multiplayer-engine test.Uvecavac");
			InputStreamReader inputReaderP2 = new InputStreamReader(processP2.getInputStream());
			OutputStreamWriter outputWriterP2 = new OutputStreamWriter(processP2.getOutputStream());


			BlockingQueue<String> starterQueue = new ArrayBlockingQueue<String>(3);
			BlockingQueue<String> resultQueue = new ArrayBlockingQueue<String>(3);
			BlockingQueue<String> communicationQueue = new ArrayBlockingQueue<String>(3);

			TimeoutRunnable timeoutRunnable = new TimeoutRunnable(communicationQueue, starterQueue, resultQueue);
			Thread timeoutThread = new Thread(timeoutRunnable);
			timeoutThread.start();

			CodeRunnable codeRunnable = new CodeRunnable(inputReader, communicationQueue);
			Thread codeExecutionThread = new Thread(codeRunnable);
			codeExecutionThread.start();


			BlockingQueue<String> starterQueueP2 = new ArrayBlockingQueue<String>(3);
			BlockingQueue<String> resultQueueP2 = new ArrayBlockingQueue<String>(3);
			BlockingQueue<String> communicationQueueP2 = new ArrayBlockingQueue<String>(3);

			TimeoutRunnable timeoutRunnableP2 = new TimeoutRunnable(communicationQueueP2, starterQueueP2, resultQueueP2);
			Thread timeoutThreadP2 = new Thread(timeoutRunnableP2);
			timeoutThreadP2.start();

			CodeRunnable codeRunnableP2 = new CodeRunnable(inputReaderP2, communicationQueueP2);
			Thread codeExecutionThreadP2 = new Thread(codeRunnableP2);
			codeExecutionThreadP2.start();


//			char[] charBuffer = new char[1024];
			for(int number : numbers) {
				outputWriter.write(number + "\n");
				outputWriter.flush();
				outputWriterP2.write(number + "\n");
				outputWriterP2.flush();
				starterQueue.put("GO");
				starterQueueP2.put("GO");
				System.out.println("P1: " + resultQueue.take());
				System.out.println("P2: " + resultQueueP2.take());

//				int length = inputReader.read(charBuffer);
//				System.out.println("Got: " + String.valueOf(Arrays.copyOfRange(charBuffer, 0, length)));
			}
			Thread.sleep(2000);
//			timeoutThread.interrupt();
//			codeExecutionThread.interrupt();



			/*int n2;
			char[] c2 = new char[1024];
			StringBuilder standardError = new StringBuilder();
			while ((n2 = errorReader.read(c2)) > 0) {
				standardError.append(c2, 0, n2);
				System.out.println("Standard Error: " + standardError.toString());
			}*/
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class TimeoutRunnable implements Runnable {

	BlockingQueue<String> targetQueue;
	BlockingQueue<String> starterQueue;
	BlockingQueue<String> resultQueue;

	public TimeoutRunnable(BlockingQueue<String> targetQueue, BlockingQueue<String> starterQueue, BlockingQueue<String> resultQueue) {
		this.targetQueue = targetQueue;
		this.starterQueue = starterQueue;
		this.resultQueue = resultQueue;
	}

	@Override
	public void run() {
		try {
			while(!"END".equals(starterQueue.take())) {
				String result = targetQueue.poll(3500, TimeUnit.MILLISECONDS);
				resultQueue.put(null == result ? "TIMEOUTO" : result);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

class CodeRunnable implements Runnable {

	InputStreamReader streamReader;
	BlockingQueue<String> queue;

	public CodeRunnable(InputStreamReader isr, BlockingQueue<String> queue) {
		this.streamReader = isr;
		this.queue = queue;
	}

	@Override
	public void run() {
		char[] charBuffer = new char[1024];
		int length;
		try {
			while ((length = streamReader.read(charBuffer)) > 0) {
				String iterationResult = String.valueOf(Arrays.copyOfRange(charBuffer, 0, length));
				iterationResult = iterationResult.replace("\n", "").replace("\r", "");
				try {
					String res = "Rezultat koda:" + iterationResult + "x";
					queue.put(res); // !!!
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			System.out.println("GRESKA !!!"); // todo: log IO exception
		}
//		System.out.println("Got: " + iterationResult);
	}
}