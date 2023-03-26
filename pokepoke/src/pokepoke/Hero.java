package pokepoke;

import java.util.Random;

public class Hero extends Unit implements MonsterAttackable {

	private int attackDamage;
	private int buffedCount;
	private int debuffedCount;

	public Hero(String name) {
		super(300, 300, 10, name);
		this.buffedCount = 3;
		this.debuffedCount = 1;
	}

	public void attack(Monster mon) {

		if (mon instanceof Boss) { // 보스 몹 일때

			// have to fill

		} else if (mon instanceof HeroAttackable) {

			this.attackDamage = (int) ((ran.nextInt(20) + 8) * (super.getAttackPower() * 1.5));

			if (this.buffedCount > 0) {
				Critical();
				buffed();
			}

			if (this.debuffedCount > 0)
				debuffed();

			mon.setHp(mon.getHp() - attackDamage);

			if (mon.getHp() <= 0)
				mon.setHp(0);

			System.out.printf("☠️ 히어로의 공격! %d 의 데미지를 입혔습니다! ☠️\n", this.attackDamage);
			System.out.printf("[현재 %s의 남은 HP] %d / %d \n", mon.getName(), mon.getHp(), mon.MAX_HP);

		}

	}

	private void Critical() {
		int dice = ran.nextInt(6) + 1;

		if (dice == 3) {
			System.out.println("[필살기 발동!!데미지 10배!!] 용의~~콧물~~~~~초~~필쌀기~~~");
			this.attackDamage *= 10;
			this.buffedCount--;
		}
	}

	private void buffed() {
		int dice = ran.nextInt(6) + 1;

		if (dice == 6) {
			System.out.println("[천상의 축복!] HP가 100 회복됩니다.");
			this.setHp(this.getHp() + 100);
			this.buffedCount--;
		}
	}

	private void debuffed() {
		int dice = ran.nextInt(6) + 1;

		if (dice == 4) {
			System.out.println("[함정이다!!] 함정에 빠져 다음 공격은 무효화됩니다.");
			this.attackDamage = 0;
			this.debuffedCount--;
		}
	}

}
