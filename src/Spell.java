import java.util.ArrayList;

//the super class of all kinds of spell
public abstract class Spell extends Merchandise implements Castable{
    public Spell(){}

    //create spell with attributes
    public Spell(String name, int price, int minLevel, int damage, int manaCost){
        super(name, minLevel, price, "Spell");
        setDamage(damage);
        setManaCost(manaCost);
        equipBehavior = new EquipOnce(this);
    }

    public Spell(String[] data){
        this(data[0], Integer.parseInt(data[1]), Integer.parseInt(data[2]), Integer.parseInt(data[3]), Integer.parseInt(data[4]));
    }

    public ArrayList<StringBuilder> getDisplayLines(){
        ArrayList<StringBuilder> attributes = new ArrayList<>();

        attributes.add(new StringBuilder("Name: " + getName()));

        attributes.add(new StringBuilder("Price: " + getPrice()));

        attributes.add(new StringBuilder("Level: " + getMinLevel()));

        attributes.add(new StringBuilder("Damage: " + getDamage()));

        attributes.add(new StringBuilder("Mana Cost: " + getManaCost()));

        attributes.add(new StringBuilder("Effect: " + getSpecil()));
        return attributes;
    }

    @Override
    public int cast(){
        return getDamage();
    }

    protected abstract void specialEffect(Monster monster);

    public int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    public int getManaCost() {
        return manaCost;
    }

    private void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public String getSpecil() {
        return specil;
    }

    public void setSpecil(String specil) {
        this.specil = specil;
    }

    private int damage;
    private int manaCost;
    private String specil;
}
