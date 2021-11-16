import java.util.ArrayList;

//the entity of armor
public class Armor extends Merchandise{
    public Armor(){}

    //create armor with attributes
    public Armor(String name, int price, int minLevel, int damageReduction){
        super(name, minLevel, price, "Armor");
        setDamageReduction(damageReduction);
        equipBehavior = new EquipOnce(this);
    }

    //create armor with attributes in string list form
    public Armor(String[] data){
        this(data[0],Integer.parseInt(data[1]),
                Integer.parseInt(data[2]),Integer.parseInt(data[3]));
    }

    //get lines when print the merchandise
    public ArrayList<StringBuilder> getDisplayLines(){
        ArrayList<StringBuilder> attributes = new ArrayList<>();

        attributes.add(new StringBuilder("Name: " + getName()));

        attributes.add(new StringBuilder("Price: " + getPrice()));

        attributes.add(new StringBuilder("Level: " + getMinLevel()));

        attributes.add(new StringBuilder("Damage Reduction: " + getDamageReduction()));
        return attributes;
    }

    public int getDamageReduction() {
        return damageReduction;
    }

    private void setDamageReduction(int damageReduction) {
        this.damageReduction = damageReduction;
    }

    //the value of damage reduction cast by the armor
    private int damageReduction;
}
