package pa1;

import java.util.Scanner;

public class Player extends GameObject{
	
	Scanner sc = new Scanner(System.in);
	
	public Player() {
		System.out.println("input player x, y (0 ~ 14) :");
		setX(sc.nextInt());
		setY(sc.nextInt());
	}	
	
	public void move(int y) {
		setY(y);
	}
}