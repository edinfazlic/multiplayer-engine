package test;

import java.util.Scanner;

public class TestOutput {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
//		while (in.hasNext()) {
//			System.out.println(in.next());
//		}

		System.out.println("This is output to standard output - " + in.nextLine());
		System.err.println("This is output to standard error");
	}
}