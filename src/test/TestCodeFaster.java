package test;

import java.util.Scanner;

public class TestCodeFaster {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("start");
//		while (in.hasNext()) {
//			System.out.println('a' + in.nextLine() + 'a');
//		}

		System.out.println("This is output to standard output - " + in.nextLine());
		System.err.println("This is output to standard error");
	}
}