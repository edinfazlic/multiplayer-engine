package test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RuntimeExecTest2 {

	public static void main(String[] args) {
		try {
			Runtime runTime = Runtime.getRuntime();
			Process process = runTime.exec("java -classpath C:\\Users\\Edin\\IdeaProjects\\MultiplayerEngine\\out\\production\\MultiplayerEngine test.TestOutput");
			OutputStream outputStream = process.getOutputStream();
			OutputStreamWriter osr = new OutputStreamWriter(outputStream);
			osr.write("kanta");

			InputStream inputStream = process.getInputStream();
			InputStreamReader isr = new InputStreamReader(inputStream);
			InputStream errorStream = process.getErrorStream();
			InputStreamReader esr = new InputStreamReader(errorStream);

			int n1;
			char[] c1 = new char[1024];
			StringBuilder standardOutput = new StringBuilder();
			System.out.println(" x x ");
			while ((n1 = isr.read(c1)) > 0) {
				System.out.println(" x x ");
				standardOutput.append(c1, 0, n1);
			}
			System.out.println("Standard Output: " + standardOutput.toString());

			int n2;
			char[] c2 = new char[1024];
			StringBuilder standardError = new StringBuilder();
			while ((n2 = esr.read(c2)) > 0) {
				standardError.append(c2, 0, n2);
			}
			System.out.println("Standard Error: " + standardError.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}