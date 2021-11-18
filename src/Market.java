import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//the entity of market
public class Market{
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

    public String displayPotions(int index){
        return Displayer.listDisplay(potions,"Potions",index);
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

    public Merchandise getItem(int index){
        if(index < weapons.size()){
            return weapons.get(index);
        }
        index-= weapons.size();
        if(index < + armors.size()){
            return armors.get(index);
        }
        index-= armors.size();
        if(index < spells.size()){
            return spells.get(index);
        }
        else {
            index-= spells.size();
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
