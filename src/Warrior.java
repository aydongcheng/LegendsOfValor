//the entity of warrior
public class Warrior extends Hero{
    public Warrior(){}

    public Warrior(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExp){
        super(name, mana, strength, agility, dexterity, startingMoney, startingExp);
    }

    public Warrior(String[] attributes){
        super(attributes);
    }

    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setStrength((int) Math.ceil(getStrength()*(1.1/1.05)));
        setAgility((int) Math.ceil(getAgility()*(1.1/1.05)));
    }
}
