package pokepoke;

public class SharkMan extends Monster implements HeroAttackable {

	private int attackDamage;

	public SharkMan() {
		super(180, 180, 8, "샤크맨");
	}

	@Override
	public String toString() {
		return "[샤크맨 출현!!!!] 야생의 샤크맨이 나타났다!!!!";
	}

	@Override
	public void attack(Hero hero) {
		if (hero instanceof MonsterAttackable) {

			this.attackDamage = (int) ((super.ran.nextInt(20) + 8) * (super.getAttackPower() * 1.3));

			hero.setHp(hero.getHp() - this.attackDamage);

			if (hero.getHp() <= 0)
				hero.setHp(0);

			System.out.printf("%s의 몸통박치기하고깨물기..! %s에게 %d의 데미지를 입혔다!\n", this.getName(), hero.getName(),
					this.attackDamage);
			System.out.printf("[PLAYER %s의 남은 HP] %d / %d \n", hero.getName(), hero.getHp(), hero.MAX_HP);

			bloodAbsorption();

		}
	}

	@Override
	public void bloodAbsorption() {
		super.setHp(super.getHp() + this.attackDamage / 50);
		
		if(super.getHp() >= super.MAX_HP)
			super.setHp(super.MAX_HP);
		
		System.out.printf("[손톱할퀴기!!!] %s의 HP가 %d 회복되었습니다.\n", this.getName(), super.getHp() + this.attackDamage / 50);
	}

}
