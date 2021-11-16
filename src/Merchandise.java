import java.util.ArrayList;

//the entity of merchandise
public abstract class Merchandise {
    public Merchandise(){}

    //create merchandise with attributes
    public Merchandise(String name, int minLevel, int price, String type){
        setName(name);
        setMinLevel(minLevel);
        setPrice(price);
        setType(type);
    }

    public int getMinLevel() {
        return minLevel;
    }

    private void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getPrice() {
        return price;
    }

    private void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    private void setType(String type) {
        this.type = type;
    }

    public abstract ArrayList<StringBuilder> getDisplayLines();

    public void display(){
        Displayer.displayLines(getDisplayLines());
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public boolean equals(Object obj) {
        return name.equals(((Merchandise) obj).getName());
    }

    public boolean isEquipped() {
        return isEquipped;
    }

    public void setEquipped(boolean equipped) {
        isEquipped = equipped;
    }

    public void equip(){
        equipBehavior.equip();
    }

    public void unequip(){
        equipBehavior.unequip();
    }

    protected Equipable equipBehavior;
    private boolean isEquipped;
    private String name;
    private int minLevel;
    private int price;
    private String type;
}
