import java.util.ArrayList;
import java.util.List;

//the entity of market
public class Market {
    public Market(){
        FileReader fileReader = new FileReader();
        //create weapons
        weapons = new ArrayList<>();
        List<String> lines = fileReader.readFile("Weaponry");
        weanponCreator = new WeanponCreator();
        for(int i = 1; i < lines.size(); i++){
            if(lines.get(i).equals(""))
                break;
            weapons.add((Weapon) weanponCreator.createMercandise(lines.get(i)));
        }

        //create armors
        armors = new ArrayList<>();
        lines = fileReader.readFile("Armory");
        armorCreator = new ArmorCreator();
        for(int i = 1; i < lines.size(); i++){
            if(lines.get(i).equals(""))
                break;
            armors.add((Armor) armorCreator.createMercandise(lines.get(i)));
        }

        //create potions
        potions = new ArrayList<>();
        lines = fileReader.readFile("Potions");
        potionCreator = new PotionCreator();
        for(int i = 1; i < lines.size(); i++){
            if(lines.get(i).equals(""))
                break;
            potions.add((Potion) potionCreator.createMercandise(lines.get(i)));
        }

        //create spells
        spells = new ArrayList<>();
        for(String fileName : new String[]{"IceSpells","FireSpells","LightningSpells"}) {
            lines = fileReader.readFile(fileName);
            spellCreator = new SpellCreator();
            for (int i = 1; i < lines.size(); i++) {
                if(lines.get(i).equals(""))
                    break;
                spells.add((Spell) spellCreator.createMercandise(lines.get(i)+"  "+fileName));
            }
        }
    }

    //display items in market

    public void displayPotions(int index){
        Displayer.listDisplay(potions,"Potions",index);
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

    public Merchandise displayItems(int index){
        if(index < weapons.size()){
            weapons.get(index).display();
            return weapons.get(index);
        }
        index-= weapons.size();
        if(index < + armors.size()){
            armors.get(index).display();
            return armors.get(index);
        }
        index-= armors.size();
        if(index < spells.size()){
            spells.get(index).display();
            return spells.get(index);
        }
        else {
            index-= spells.size();
            potions.get(index).display();
            return potions.get(index);
        }
    }

    private ArrayList<Weapon> weapons;
    private WeanponCreator weanponCreator;
    private ArrayList<Armor> armors;
    private ArmorCreator armorCreator;
    private ArrayList<Potion> potions;
    private PotionCreator potionCreator;
    private ArrayList<Spell> spells;
    private SpellCreator spellCreator;
}
