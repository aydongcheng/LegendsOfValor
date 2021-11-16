//the entity of fire spell
public class FireSpell extends Spell{
    public FireSpell(){}

    public FireSpell(String name, int price, int minLevel, int damage, int manaCost){
        super(name,price,minLevel,damage,manaCost);
        setSpecil("reduce 10% defense");
    }

    public FireSpell(String[] data) {
        super(data);
        setSpecil("reduce 10% defense");
    }

    //cast special effect to monster
    @Override
    protected void specialEffect(Monster monster) {
        monster.setDefense((int) Math.ceil(monster.getDefense()*0.9));
    }
}
