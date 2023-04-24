package pa1;

import java.util.Scanner;

public class Ghost extends GameObject{
	
	Scanner sc = new Scanner(System.in);

	public Ghost() {
		System.out.println("input ghost x, y (0 ~ 14) :");
		setX(sc.nextInt());
		setY(sc.nextInt());
	}	
	
	public void move(int y) {
		setY(y);
	}
	
}