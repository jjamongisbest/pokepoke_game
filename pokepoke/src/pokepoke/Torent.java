package pokepoke;

public class Torent extends Monster implements HeroAttackable {

	private int attackDamage;

	public Torent() {
		super(250, 250, 13, "토렌트");
	}

	@Override
	public String toString() {
		return "[토렌트 출현!!!!!] 야생의 토렌트가 출몰했다!!!!!";
	}

	@Override
	public void attack(Hero hero) {
		if (hero instanceof MonsterAttackable) {

			this.attackDamage = (int) ((super.ran.nextInt(30) + 15) * (super.getAttackPower() * 1.8));

			hero.setHp(hero.getHp() - this.attackDamage);

			if (hero.getHp() <= 0)
				hero.setHp(0);

			System.out.printf("%s의 나무줄기채찍!!!! %s에게 %d의 데미지를 입혔다!\n", this.getName(), hero.getName(), this.attackDamage);
			System.out.printf("[PLAYER %s의 남은 HP] %d / %d \n", hero.getName(), hero.getHp(), hero.MAX_HP);

			bloodAbsorption();

		}

	}

	@Override
	public void bloodAbsorption() {
		super.setHp(super.getHp() + this.attackDamage / 2);
		
		if(super.getHp() >= super.MAX_HP)
			super.setHp(super.MAX_HP);
		
		System.out.printf("[뿌리내리기!!!] %s의 HP가 %d 회복되었습니다.\n", this.getName(), super.getHp() + this.attackDamage / 2);
	}

}
