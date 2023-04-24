package pa1;

import java.util.Scanner;

public class Door extends GameObject{
	
	Scanner sc = new Scanner(System.in);
	
	public Door() {
		System.out.println("input door x, y (0 ~ 14) :");
		setX(sc.nextInt());
		setY(sc.nextInt());
	}
	
}