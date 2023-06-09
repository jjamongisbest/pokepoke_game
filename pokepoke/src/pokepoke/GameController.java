package pokepoke;

import java.util.Random;
import java.util.Scanner;

public class GameController {

	public static final Random ran = new Random();

	public static int random(int size) {
		return ran.nextInt(size);
	}

	private Scanner sc;

	private final int SIZE;

	private final int WALL;
	private final int HERO;
	private final int ROAD;

	private int map[][];

	private int pX;
	private int pY;

	private String[] monsters;
	private Monster mon;

	private int exp;

	private Hero hero;

	public GameController() {

		this.sc = new Scanner(System.in);

		System.out.print("[닉네임설정] : ");
		String name = this.sc.next();

		this.hero = new Hero(name);

		this.SIZE = 15;

		this.WALL = 1;
		this.HERO = 2;
		this.ROAD = 0;

		this.map = new int[SIZE][SIZE];

		this.pX = 0;
		this.pY = 0;

		this.exp = 0;

		this.monsters = new String[] { "SharkMan", "PoisionSlime", "Torent", "IceTurtle", "FireDragon" };
	}

	public void run() {

		setMap();

		while (true) {
			printMap();
			movePlayer();
			monsterAppearance();
			if (this.exp % 3 == 0 && this.exp != 0)
				levelUp();
		}

	}

	private void levelUp() {

		System.out.println("[✨LEVEL UP✨] 전장의 축복이 내려집니다! ");

		hero.max_hp += 100;
		hero.setHp(hero.max_hp);
		hero.setAttackPower(hero.getAttackPower() + 10);

	}

	private void monsterAppearance() {
//		int dice = ran.nextInt(10) + 1;
		int dice = 1;

		if (dice == 1) {
			System.err.println("[경고!] 비릿한 물비린내가 난다...몬스터를 만난 것 같다");
			choice();
			int sel = this.sc.nextInt();

			if (sel == 1) {
				fight(0);
			} else {
				System.out.print("[최선의선택이다!!!] 도망가다 구르는 바람에 HP가 2 하락했습니다.");
				this.hero.setHp(this.hero.getHp() - 2);
			}

		} else if (dice == 3) {

			System.err.println("[경고!] 끈적한 무언가를 밟았다! 몬스러틑 만난 것 같다");
			choice();
			int sel = this.sc.nextInt();

			if (sel == 1) {
				fight(1);
			} else {
				System.out.println("[최선의선택이다!!!] 도망가다 발을 삐끗해서 HP가 5 하락했습니다.");
				this.hero.setHp(this.hero.getHp() - 5);
			}

		} else if (dice == 6) {

			System.err.println("[경고!] 나무줄기가 발목을 휘감고있다.. 몬스터를 만난 것 같다");
			choice();
			int sel = this.sc.nextInt();

			if (sel == 1) {
				fight(2);
			} else {
				System.out.println("[최선의선택이다!!!] 도망가다 자존심이 상해서 HP가 10 하락했습니다.");
				this.hero.setHp(this.hero.getHp() - 10);
			}

		} else if (dice == 7) {

			System.err.println("[경고!] 뼈에 사무치는 한기가 느껴진다..! 몬스터를 만난 것 같다. ");
			choice();
			int sel = this.sc.nextInt();

			if (sel == 1) {
				fight(3);
			} else {
				System.out.println("[최선의선택이다!!!] 도망가다 물에 빠져 HP가 15 하락했습니다.");
				this.hero.setHp(this.hero.getHp() - 15);
			}

		} else if (dice == 10) {

			System.err.println("[경고!] 전설의 용이다!!!!!!!!!!");
			choice();
			int sel = this.sc.nextInt();

			if (sel == 1) {
				fight(4);
			} else {
				System.out.println("[최선의선택이다!!!] 도망가다 뒤에서 기습을 당해 HP가 20 하락했습니다.");
				this.hero.setHp(this.hero.getHp() - 20);
			}

		}

	}

	private void choice() {
		System.out.println("[1] 싸우기");
		System.out.println("[2] 도망치기");
		System.out.print("[어떡할까?] : ");
	}

	private void fight(int index) {
		String className = "pokepoke." + monsters[index];

		try {
			Class<?> clazz = Class.forName(className);

			Object object = clazz.getDeclaredConstructor().newInstance();

			if (object instanceof Monster) {
				this.mon = (Monster) object;
			}

			System.out.println("[BATTLE ON]");

			while (true) {

				if (this.mon == null)
					System.out.println("this.mon is not defined");

				this.hero.attack(this.mon);

				if (this.mon.getHp() <= 0) {
					System.out.printf("[%s를 잡았다!!] 경험치를 얻었다.\n", this.mon.getName());
					this.exp++;
					break;
				}

				this.mon.attack(this.hero);

				if (this.hero.getHp() <= 0) {
					System.out.printf("%s님이 죽었습니다. GAME LOSE\n", this.hero.getName());
					System.out.println("[칠칠맞은 당신을 위해 생명의 버프가 내려졌습니다.]");
					this.hero.setHp(20);
					break;
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void movePlayer() {
		System.out.println(" 	     ▲(w)      ");
		System.out.println("	◀(a) ▼(s) ▶(d)");
		System.out.printf(" [현재 체력] %d / %d  [AP] %d \n", this.hero.getHp(), this.hero.max_hp,
				this.hero.getAttackPower());
		System.out.print("     [MOVE] : ");

		String move = this.sc.next();

		int y = this.pY;
		int x = this.pX;

		if (move.equals("w")) { // 상
			y--;
		} else if (move.equals("a")) { // 좌
			x--;
		} else if (move.equals("s")) { // 하
			y++;
		} else if (move.equals("d")) { // 우
			x++;
		}

		if (x < 0 || x >= this.SIZE || y < 0 || y >= this.SIZE || map[y][x] == this.WALL)
			return;

		map[pY][pX] = 0;

		this.pY = y;
		this.pX = x;

		this.map[pY][pX] = this.HERO;
	}

	private void printMap() {
		for (int i = 0; i < this.SIZE; i++) {
			for (int k = 0; k < this.SIZE; k++) {
				int block = this.map[i][k];
				if (block == this.ROAD)
					System.out.print("  ");
				else if (block == this.WALL)
					System.out.print("■ ");
				else if (block == this.HERO)
					System.out.print("○ ");
			}
			System.out.println();

		}
	}

	private void setMap() {

		for (int i = 0; i < this.SIZE; i++) {
			this.map[0][i] = this.WALL;
			this.map[i][0] = this.WALL;
			this.map[this.SIZE - 1][i] = this.WALL;
			this.map[i][this.SIZE - 1] = this.WALL;
		}

		int wallCnt = 60;
		while (wallCnt != 0) {
			int rY = ran.nextInt(this.SIZE);
			int rX = ran.nextInt(this.SIZE);

			if (this.map[rY][rX] == 0) {
				this.map[rY][rX] = this.WALL;
				wallCnt--;
			}
		}

		int playerTmp = 0;
		while (playerTmp != 1) {
			int rY = ran.nextInt(this.SIZE);
			int rX = ran.nextInt(this.SIZE);

			if (this.map[rY][rX] == 0) {
				this.map[rY][rX] = this.HERO;

				this.pY = rY;
				this.pX = rX;
				playerTmp++;
			}
		}
	}

}
