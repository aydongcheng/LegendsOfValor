//the entity of ice spell
public class IceSpell extends Spell{
    public IceSpell(){}

    public IceSpell(String name, int price, int minLevel, int damage, int manaCost){
        super(name,price,minLevel,damage,manaCost);
        setSpecil("reduce 10% damage");
    }

    public IceSpell(String[] data) {
        super(data);
        setSpecil("reduce 10% damage");
    }

    //cast special effect to monster
    @Override
    protected void specialEffect(Monster monster) {
        monster.setDamage((int) Math.ceil(monster.getDamage()*0.9));
    }
}
