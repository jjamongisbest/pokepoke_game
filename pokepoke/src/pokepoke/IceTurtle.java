package pokepoke;

public class IceTurtle extends Monster implements HeroAttackable {

	private int attackDamage;

	public IceTurtle() {
		super(450, 450, 24, "아이스꺼북");
	}

	@Override
	public void attack(Hero hero) {
		if (hero instanceof MonsterAttackable) {

			this.attackDamage = (int) ((super.ran.nextInt(24) + 12) * (super.getAttackPower() * 1.7));

			hero.setHp(hero.getHp() - this.attackDamage);

			if (hero.getHp() <= 0)
				hero.setHp(0);

			System.out.printf("%s의 꼬리휘둘러치기...! %s에게 %d의 데미지를 입혔다!!!\n", super.getName(), hero.getName(),
					this.attackDamage);
			System.out.printf("[PLAYER %d의 남은 HP] %d / %d \n", hero.getName(), hero.getHp(), hero.MAX_HP);

			bloodAbsorption();
		}
	}

	private void bloodAbsorption() {
		super.setHp(super.getHp() + this.attackDamage / 2);
		System.out.printf("[얼음송곳!!] %s의 HP가 %d 회복되었습니다.\n", super.getName(), super.getHp() + this.attackDamage / 2);
	}

}
