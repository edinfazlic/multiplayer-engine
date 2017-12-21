package test;

import java.util.Scanner;

public class Uvecavac {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.err.println("Normal speed counter started.");
		while (true) {
			String number = in.nextLine();
			int i = Integer.valueOf(number);
			i += 2;
			try {
				Thread.sleep(i * 1000);
			} catch (InterruptedException e) {
				System.err.println("No errors unless completely necessary. Seriously!");
			}
			System.out.println("Uvecavam za 2: " + i);
		}
	}
}