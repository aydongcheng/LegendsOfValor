import java.util.ArrayList;

//the entity of weapon
public class Weapon extends  Merchandise implements Attackable{
    public Weapon(){}

    public Weapon(String name, int price, int minLevel, int damage){
        super(name, minLevel, price, "Weapon");
        setDamage(damage);
        equipBehavior = new EquipOnce(this);
    }

    public Weapon(String[] data){
        this(data[0],Integer.parseInt(data[1]),
                Integer.parseInt(data[2]),Integer.parseInt(data[3]));
    }

    public ArrayList<StringBuilder> getDisplayLines(){
        ArrayList<StringBuilder> attributes = new ArrayList<>();

        attributes.add(new StringBuilder("Name: " + getName()));

        attributes.add(new StringBuilder("Price: " + getPrice()));

        attributes.add(new StringBuilder("Level: " + getMinLevel()));

        attributes.add(new StringBuilder("Damage: " + getDamage()));
        return attributes;
    }

    @Override
    public int attack() {
        return getDamage();
    }

    public int getDamage() {
        return damage;
    }

    private void setDamage(int damage) {
        this.damage = damage;
    }

    private int damage;
}
