import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//the entity of inventory
public class Inventory {
    public Inventory(){
        weapons = new ArrayList<>();
        armors = new ArrayList<>();
        potions = new HashMap<>();
        spells = new ArrayList<>();
    }

    //add weapon to inventory
    public void addWeapon(Weapon weapon){
        weapons.add(weapon);
    }

    public void sellWeapon(Weapon weapon){
        weapons.remove(weapon);
    }

    public void addArmor(Armor armor){
        armors.add(armor);
    }

    public void sellArmor(Armor armor){
        armors.remove(armor);
    }

    public void addPotion(Potion potion){
        if(potions.containsKey(potion))
            potions.put(potion, potions.get(potion)+1);
        else
            potions.put(potion,1);
    }

    public void sellPotion(Potion potion){
        if(potions.get(potion) == 1)
            potions.remove(potion);
        else
            potions.put(potion, potions.get(potion)-1);
    }

    public void addSpell(Spell spell){
        spells.add(spell);
    }

    public void sellSpell(Spell spell){
        spells.remove(spell);
    }

    public Map<Potion, Integer> getPotions() {
        return potions;
    }

    //display potions and the number of potions
    public void displayPotions(int index){
        ArrayList<String> strings = new ArrayList<>();
        potions.forEach((k, v) -> strings.add(k.getName()+" * "+v));
        Displayer.listDisplay(strings,"Potions",index);
    }

    public void displayArmors(int index){
        Displayer.listDisplay(armors,"Armors",index);
    }

    public void displaySpells(int index){
        Displayer.listDisplay(spells,"Spells",index);
    }

    public void displayWeapons(int index){
        Displayer.listDisplay(weapons,"Weapons",index);
    }

    public ArrayList<Weapon> getWeapons() {
        return weapons;
    }

    public ArrayList<Armor> getArmors() {
        return armors;
    }

    public ArrayList<Spell> getSpells() {
        return spells;
    }

    //display all items in inventory
    public int display(){
        int index = 0;
        displayWeapons(index);
        index += weapons.size();
        displayArmors(index);
        index += armors.size();
        displaySpells(index);
        index += spells.size();
        displayPotions(index);
        return index+ potions.size();
    }

    //find the item with its index
    public Merchandise displayItems(int index){
        if(index < weapons.size()){
            weapons.get(index).display();
            return weapons.get(index);
        }
        index-=weapons.size();
        if(index < armors.size()){
            armors.get(index).display();
            return armors.get(index);
        }
        index-=armors.size();
        if(index <spells.size()){
            spells.get(index).display();
            return spells.get(index);
        }
        else {
            index-=spells.size();
            new ArrayList<>(potions.keySet()).get(index).display();
            return new ArrayList<>(potions.keySet()).get(index);
        }
    }

    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;
    private Map<Potion, Integer> potions;
    private ArrayList<Spell> spells;
}
