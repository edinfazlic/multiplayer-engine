package test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class MainEngine {

	private static int[] numbers = {1, 2, 1, 2, 1};

	public static void main(String[] args) {
		try {
			Runtime runTime = Runtime.getRuntime();
//			Process compile = runTime.exec("javac -classpath C:\\Projects\\multiplayer-engine TestCode.java");
//			compile.waitFor(); // compiling doesn't execute instantly
//			Process process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine TestCode");
			Process process = runTime.exec("java -classpath C:\\Projects\\multiplayer-engine\\out\\production\\multiplayer-engine test.TestCode");
			InputStreamReader inputReader = new InputStreamReader(process.getInputStream());
//			InputStreamReader errorReader = new InputStreamReader(process.getErrorStream());
			OutputStreamWriter outputWriter = new OutputStreamWriter(process.getOutputStream());


			char[] charBuffer = new char[1024];
			for(int number : numbers) {
				outputWriter.write(number + "\n");
				outputWriter.flush();

				int length = inputReader.read(charBuffer);
				System.out.println("Got: " + String.valueOf(Arrays.copyOfRange(charBuffer, 0, length)));
			}



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