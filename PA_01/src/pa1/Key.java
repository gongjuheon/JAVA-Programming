package pa1;

import java.util.Scanner;

public class Key extends GameObject{
	
	Scanner sc = new Scanner(System.in);

	public Key() {
		System.out.println("input key x, y (0 ~ 14) :");
		setX(sc.nextInt());
		setY(sc.nextInt());
	}
}