package pokepoke;

public class FireDragon extends Monster implements HeroAttackable {

	private int attackDamage;

	public FireDragon() {
		super(500, 500, 30, "파이어드래곤");
	}

	@Override
	public void attack(Hero hero) {
		if (hero instanceof MonsterAttackable) {

			this.attackDamage = (int) ((super.ran.nextInt(super.getAttackPower()) + 15) * (super.getAttackPower() * 2.0));

			hero.setHp(hero.getHp() - this.attackDamage);

			if (hero.getHp() <= 0)
				hero.setHp(0);

			System.out.printf("%s의 나무줄기채찍!!!! %s에게 %d의 데미지를 입혔다!\n", super.getName(), hero.getName(),
					this.attackDamage);
			System.out.printf("[PLAYER %d의 남은 HP] %d / %d \n", hero.getName(), hero.getHp(), hero.MAX_HP);

			bloodAbsorption();

		}

	}

	private void bloodAbsorption() {
		super.setHp(super.getHp() + this.attackDamage / 2);
		System.out.printf("[뿌리내리기!!!] %s의 HP가 %d 회복되었습니다.\n", this.getName(), super.getHp() + this.attackDamage / 2);
	}

}
