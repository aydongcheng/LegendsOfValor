//the entity of sorcerer
public class Sorcerer extends Hero{
    public Sorcerer(){}

    public Sorcerer(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExp){
        super(name, mana, strength, agility, dexterity, startingMoney, startingExp);
    }

    public Sorcerer(String[] attributes){
        super(attributes);
    }

    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setDexterity((int) Math.ceil(getDexterity()*(1.1/1.05)));
        setAgility((int) Math.ceil(getAgility()*(1.1/1.05)));
    }
}
