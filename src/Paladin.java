//the entity of paladin
public class Paladin extends Hero{
    public Paladin(){}

    public Paladin(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExp){
        super(name, mana, strength, agility, dexterity, startingMoney, startingExp);
    }

    public Paladin(String[] attributes){
        super(attributes);
    }

    @Override
    public void levelUpTo(int Level){
        super.levelUpTo(Level);
        setDexterity((int) Math.ceil(getDexterity()*(1.1/1.05)));
        setStrength((int) Math.ceil(getStrength()*(1.1/1.05)));
    }
}
