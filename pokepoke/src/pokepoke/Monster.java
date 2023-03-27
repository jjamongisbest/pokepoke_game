package pokepoke;

import java.util.Random;

abstract public class Monster {
	
	private int hp;
	private int attackPower;
	private String name;
	public int max_hp;
	public Random ran;
	
	public Monster(int hp, int max, int ap, String name) {
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

	public String getName() {
		return this.name;
	}
	
	public abstract void attack(Hero hero);
	
	public abstract void bloodAbsorption();


}
