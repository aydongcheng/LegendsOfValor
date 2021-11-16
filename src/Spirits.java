//the entity of spirits
public class Spirits extends Monster{
    public Spirits(){}

    public Spirits(String name, int initLevel, int damage, int defense, int dodgeChance){
        super(name, initLevel, damage, defense, dodgeChance);
    }

    public Spirits(String[] attributes){
        super(attributes);
    }

    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setDodgeChance((int) Math.ceil(getDodgeChance()*Math.pow((1.1/1.05),(Level - getInitLevel()))));
    }
}
