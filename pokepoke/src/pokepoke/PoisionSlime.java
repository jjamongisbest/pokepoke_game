package pokepoke;

public class PoisionSlime extends Monster implements HeroAttackable {

	private int attackDamage;

	public PoisionSlime() {
		super(340, 340, 24, "포이즌슬라임");
	}

	@Override
	public String toString() {
		return "[포이즌슬라임 출현!!!!] 이상한 검은물체가 나타났다!!!!";
	}

	@Override
	public void attack(Hero hero) {
		if (hero instanceof MonsterAttackable) {

			this.attackDamage = (int) ((super.ran.nextInt(super.getAttackPower()) + 7)
					* (super.getAttackPower() * 1.7));

			hero.setHp(hero.getHp() - this.attackDamage);

			if (hero.getHp() <= 0)
				hero.setHp(0);

			System.out.printf("[ %s의 지독한독가스살포! %s에게 %d의 데미지를 입혔다! ]\n", super.getName(), hero.getName(),
					this.attackDamage);
			System.out.printf("[PLAYER %s의 남은 HP] %d / %d \n", hero.getName(), hero.getHp(), hero.MAX_HP);

			bloodAbsorption();

		}

	}

	@Override
	public void bloodAbsorption() {
		super.setHp(super.getHp() + this.attackDamage / 20);

		if (super.getHp() >= super.MAX_HP)
			super.setHp(super.MAX_HP);

		System.out.printf("[흡혈빨판!!] %s의 HP가 %d 회복되었습니다.\n", this.getName(), super.getHp() + this.attackDamage / 20);
	}

}
