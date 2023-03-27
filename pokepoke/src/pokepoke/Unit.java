package pokepoke;

import java.util.Random;

abstract public class Unit {

	private int hp;
	private int attackPower;
	private String name;
	public int max_hp;
	public Random ran;

	public Unit(int hp, int max, int ap, String name) {
		this.hp = hp;
		this.attackPower = ap;
		this.name = name;
		this.max_hp = max;
		this.ran = new Random();
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAttackPower() {
		return this.attackPower;
	}
	
	public void setAttackPower(int ap) {
		this.attackPower = ap;
	}

	public String getName() {
		return this.name;
	}


}
