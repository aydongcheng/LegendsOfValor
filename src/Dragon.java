//the entity of monster dragon
public class Dragon extends Monster{
    public Dragon(){}

    //create dragon with attributes
    public Dragon(String name, int initLevel, int damage, int defense, int dodgeChance){
        super(name, initLevel, damage, defense, dodgeChance);
    }

    public Dragon(String[] attributes){
        super(attributes);
    }

    //upgrade the dragon to a certain level
    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setDamage((int) Math.ceil(getDamage()*Math.pow((1.1/1.05),(Level - getInitLevel()))));
    }
}
