//the entity of monster Exoskeleton
public class Exoskeleton extends Monster {
    public Exoskeleton(){}

    //create Exoskeleton with attributes
    public Exoskeleton(String name, int initLevel, int damage, int defense, int dodgeChance){
        super(name, initLevel, damage, defense, dodgeChance);
    }

    public Exoskeleton(String[] attributes){
        super(attributes);
    }

    //upgrade the Exoskeleton to a certain level
    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setDefense((int) Math.ceil(getDefense()*Math.pow((1.1/1.05),(Level - getInitLevel()))));
    }
}
