package pa1;

import java.util.Scanner;

public class Board implements Game {
	Player player;
	Ghost ghost;
	Key key;
	Door door;
	
	char[][] board = new char[][] {
		{' ','■',' ',' ',' ',' ','■',' ','■',' ',' ',' ',' ',' ',' '},
		{' ','■',' ','■','■','■','■',' ','■',' ','■',' ','■','■',' '}, 
		{' ','■',' ',' ',' ','■',' ',' ','■',' ','■',' ',' ','■',' '},
		{' ','■',' ','■',' ','■',' ','■','■',' ','■','■',' ','■',' '}, 
		{' ',' ',' ','■',' ','■',' ',' ',' ',' ',' ',' ',' ',' ',' '},
		{' ','■','■','■',' ',' ',' ','■','■','■','■','■','■','■',' '}, 
		{' ',' ',' ',' ',' ','■',' ',' ',' ',' ',' ',' ',' ','■',' '}, 
		{'■','■',' ','■','■','■',' ','■','■','■','■','■',' ','■',' '}, 
		{' ',' ',' ',' ',' ',' ',' ','■',' ',' ',' ',' ',' ','■',' '},
		{' ','■','■',' ','■','■',' ','■',' ','■','■','■',' ','■',' '}, 
		{' ','■',' ',' ',' ',' ',' ','■',' ',' ',' ','■',' ','■',' '},
		{' ','■',' ','■','■','■','■','■','■','■','■','■',' ','■',' '}, 
		{' ','■',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ','■',' '},
		{' ','■','■','■',' ','■','■','■','■','■','■','■',' ','■',' '}, 
		{' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '}
		};

	public Board() {		
		
	}
	
	public void printBoard() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (i == player.getY() && j == player.getX())
					System.out.print("P ");
				else if (i == ghost.getY() && j == ghost.getX())
					System.out.print("G ");
				else if (i == key.getY() && j == key.getX() && !(key.getX() == -1 && key.getY() == -1))
					System.out.print("K ");
				else if (i == (door.getY() % 15) && j == (door.getX() % 15))
					System.out.print("D ");
				else {
					System.out.print(board[i][j]);
					System.out.print(' ');
				}
			}
			System.out.println();
		}
	}

	public void initObjects() {		
		// Player
		while (true) {
			player = new Player();
			if (player.getX() >= 0 && player.getX() <= 14 
					&& player.getY() >= 0 && player.getY() <= 14
					&& board[player.getY()][player.getX()] != '■')
				break;
			else
				continue;
		}
		
		// Ghost
		while (true) {
			ghost = new Ghost();
			if (ghost.getX() >= 0 && ghost.getX() <= 14 
					&& ghost.getY() >= 0 && ghost.getY() <= 14
					&& ((ghost.getX() != player.getX()) || (ghost.getY() != player.getY())))
				break;
			else
				continue;
		}
		
		// Key
		while (true) {
			key = new Key();
			if (key.getX() >= 0 && key.getX() <= 14 && key.getY() >= 0 && key.getY() <= 14
					&& board[key.getY()][key.getX()] != '■'
					&& ((key.getX() != player.getX()) || (key.getY() != player.getY()))
					&& ((key.getX() != ghost.getX())  || (key.getY() != ghost.getY())))
				break;
			else
				continue;
		}

		// Door
		while (true) {
			door = new Door();
			if (door.getX() >= 0 && door.getX() <= 14 && door.getY() >= 0 && door.getY() <= 14
					&& board[door.getY()][door.getX()] != '■'
					&& ((door.getX() != player.getX()) || (door.getY() != player.getY()))
					&& ((door.getX() != ghost.getX())  || (door.getY() != ghost.getY()))
					&& ((door.getX() != key.getX())    || (door.getY() != key.getY())))
				break;
			else
				continue;
		}
	}
	
	public void movePlayer() {
		Scanner sc = new Scanner(System.in);
		String direction = sc.next();

		try {
			if (direction.charAt(0) == 'u' && board[player.getY() - 1][player.getX()] != '■')
				player.move(player.getY() - 1);
			else if (direction.charAt(0) == 'd' && board[player.getY() + 1][player.getX()] != '■')
				player.move(player.getY() + 1);
			else if (direction.charAt(0) == 'l' && board[player.getY()][player.getX() - 1] != '■')
				player.setX(player.getX() - 1);
			else if (direction.charAt(0) == 'r' && board[player.getY()][player.getX() + 1] != '■')
				player.setX(player.getX() + 1);
			else {}	
		} catch (ArrayIndexOutOfBoundsException e) {}


		if ((player.getX() == key.getX()) && (player.getY() == key.getY())) {
			key.setX(-1);
			key.setY(-1);
		}
	}	
	
	public void moveGhost() {

		if (((door.getX() / 15) + 1) % 2 == 1) {
			if (ghost.getX() > player.getX())
				ghost.setX(ghost.getX() - 1);
			if (ghost.getX() < player.getX())
				ghost.setX(ghost.getX() + 1);
			if (ghost.getY() > player.getY())
				ghost.move(ghost.getY() - 1);
			if (ghost.getY() < player.getY())
				ghost.move(ghost.getY() + 1);	
		}
		door.setX(door.getX() + 15);
	}
	
	public boolean isFinish() {
		if (player.getX() == (door.getX() % 15) && (player.getY() == (door.getY() % 15))
				&& (player.getX() == ghost.getX() && player.getY() == ghost.getY()) 
				&& (key.getX() == -1 && key.getY() == -1)) {
			System.out.println("YOU WIN");
			return true;	
		}
		else if ((player.getX() == (door.getX() % 15)) && (player.getY() == (door.getY() % 15))
				&& (key.getX() == -1) && (key.getY() == -1)) {
			System.out.println("YOU WIN");
			return true;	
		}
		else if ((player.getX() == ghost.getX()) && (player.getY() == ghost.getY())) {
			System.out.println("YOU LOSE");
			return true;	
		}
		else return false;
	}
}