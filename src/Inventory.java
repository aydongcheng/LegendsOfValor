import java.util.ArrayList;
import java.util.Arrays;
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
    public String displayPotions(int index){
        ArrayList<String> strings = new ArrayList<>();
        potions.forEach((k, v) -> strings.add(k.getName()+" * "+v));
        return Displayer.listDisplay(strings,"Potions",index);
    }

    public String displayArmors(int index){
        return Displayer.listDisplay(armors,"Armors",index);
    }

    public String displaySpells(int index){
        return Displayer.listDisplay(spells,"Spells",index);
    }

    public String displayWeapons(int index){
        return Displayer.listDisplay(weapons,"Weapons",index);
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

    public String getDisplayLines(){
        StringBuilder infos = new StringBuilder();
        int index = 0;
        infos.append(displayWeapons(index));
        index += weapons.size();
        infos.append(displayArmors(index));
        index += armors.size();
        infos.append(displaySpells(index));
        index += spells.size();
        infos.append(displayPotions(index));
        return infos.toString();
    }

    public int getTotalItemsNum(){
        return weapons.size()+armors.size()+spells.size()+potions.size();
    }

    //find the item with its index
    public Merchandise getItem(int index){
        if(index < weapons.size()){
            return weapons.get(index);
        }
        index-=weapons.size();
        if(index < armors.size()){
            return armors.get(index);
        }
        index-=armors.size();
        if(index <spells.size()){
            return spells.get(index);
        }
        else {
            index-=spells.size();
            return new ArrayList<>(potions.keySet()).get(index);
        }
    }

    private ArrayList<Weapon> weapons;
    private ArrayList<Armor> armors;
    private Map<Potion, Integer> potions;
    private ArrayList<Spell> spells;
}
