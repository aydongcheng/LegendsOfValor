import java.util.ArrayList;
import java.util.Random;

//the entity of all kinds of heroes
public class Hero extends Characters{
    public Hero(){}

    //create hero with attributes
    public Hero(String name, int mana, int strength, int agility, int dexterity, int startingMoney, int startingExp){
        super(name);
        setMana(mana);
        setStrength(strength);
        setAgility(agility);
        setDexterity(dexterity);
        setMoney(startingMoney);
        setExp(startingExp);
        setLevel(1);
        setHp(100);
        weapon = null;
        armor = null;
        spell = null;
        inventory = new Inventory();
    }

    //create hero with attributes in string list form
    public Hero(String[] attributes){
        this(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]), Integer.parseInt(attributes[5]),
                Integer.parseInt(attributes[6]));
    }

    @Override
    public int attack() {
        int damage = getStrength();
        if(weapon!=null)
            damage += weapon.attack();
        return (int) Math.ceil(damage*0.05);
    }

    @Override
    public int getHurt(int damage) {
        Random r = new Random();
        int i = r.nextInt(100);
        if(i <= Math.ceil(getAgility()*0.002))
            return 0;
        int actualDamage = damage;
        if(armor != null)
            actualDamage = Math.max(damage - ((int) (armor.getDamageReduction()*0.01)), 0);
        if(actualDamage>getHp()){
            actualDamage =getHp();
            setFaint(true);
            Window.newMessage("Hero "+ getName() +" is dead");
        }
        else
            setHp(getHp()-actualDamage);
        return actualDamage;
    }

    public void revive(){
        setHp(50);
    }

    //cast a spell, return the spell damage
    public int SpellAttack(){
        if(spell!=null && getMana() >= spell.getManaCost()) {
            setMana(getMana()-spell.getManaCost());
            return (int) spell.cast() + (getDexterity() / 10000) * spell.cast();
        }
        else return 0;
    }

    //when hero win the fight, get the rewards from fight
    public boolean getRewards(int MonsterLevel, int exp){
        addMoney(MonsterLevel*100);
        return addExp(exp);
    }

    //hero recover after one round of the fight
    public void recover(){
        setHp((int) Math.ceil(getHp()*1.1));
        setMana((int) Math.ceil(getMana()*1.1));
    }

    //get rewards with default exp
    public boolean getRewards(int MonsterLevel){
        return getRewards(MonsterLevel,2);
    }

    public void addMoney(int money){
        setMoney(getMoney()+money);
    }

    public boolean addExp(int exp){
        if(getExp()+exp > getLevel()*10){
            setExp(getExp()+exp-getLevel()*10);
            setLevel(getLevel()+1);
            levelUpTo(getLevel());
            return true;
        }
        else {
            setExp(getExp()+exp);
            return false;
        }
    }

    @Override
    public void levelUpTo(int Level) {
        setStrength((int) Math.ceil(getStrength()*Math.pow((1.05),(Level - getLevel()))));
        setAgility((int) Math.ceil(getAgility()*Math.pow((1.05),(Level - getLevel()))));
        setDexterity((int) Math.ceil(getDexterity()*Math.pow((1.05),(Level - getLevel()))));
        setLevel(Level);
        setHp(getLevel()*100);
    }

    //buy a merchandise and store it to the inventory
    public boolean buyMerchandise(Merchandise merchandise){
        if(merchandise.getMinLevel() > getLevel() || getMoney() < merchandise.getPrice())
            return false;
        else {
            if (merchandise instanceof Weapon)
                inventory.addWeapon((Weapon) merchandise);
            else if (merchandise instanceof Armor)
                inventory.addArmor((Armor) merchandise);
            else if (merchandise instanceof Potion)
                inventory.addPotion((Potion) merchandise);
            else
                inventory.addSpell((Spell) merchandise);
        }
            setMoney(getMoney()-merchandise.getPrice());
            return true;
    }

    //sell the merchandise in the inverntory
    public void sellMerchandise(Merchandise merchandise){
        if(merchandise instanceof Weapon){
            inventory.sellWeapon((Weapon) merchandise);
            if(merchandise == getWeapon())
                setWeapon(null);
        }
        else if(merchandise instanceof Armor) {
            inventory.sellArmor((Armor) merchandise);
            if (merchandise == getArmor())
                setArmor(null);
        }
        else if(merchandise instanceof Potion) {
            inventory.sellPotion((Potion) merchandise);
        }
        else{
            inventory.sellSpell((Spell) merchandise);
            if (merchandise == getSpell())
                setSpell(null);
        }
        addMoney(merchandise.getPrice()/2);
    }

    //use a potion
    public void consumePotion(Potion potion){
        int increase = potion.consume();
        String[] attributes = potion.getAttributes();
        for(String attribute: attributes){
            switch (attribute){
                case "Health":
                    setHp(getHp()+increase);break;
                case "Strength":
                    setStrength(getStrength()+increase);break;
                case "Mana":
                    setMana(getMana()+increase);break;
                case "Dexterity":
                    setDexterity(getDexterity()+increase);break;
                case "Agility":
                    setAgility(getAgility()+increase);break;
                case "All":
                    setHp(getHp()+increase);
                    setStrength(getStrength()+increase);
                    setMana(getMana()+increase);
                    setDexterity(getDexterity()+increase);
                    setAgility(getAgility()+increase);
                    break;
            }
        }
        getInventory().sellPotion(potion);
    }

    //equip an equipment or use a potion
    public void equipOrUseMerchandise(Merchandise merchandise){
        if(merchandise instanceof Weapon)
            setWeapon((Weapon) merchandise);
        else if(merchandise instanceof Armor)
            setArmor((Armor) merchandise);
        else if(merchandise instanceof Spell)
            setSpell((Spell) merchandise);
        else
            consumePotion((Potion) merchandise);
    }

    //get the display lines
    public ArrayList<StringBuilder> getDisplayLines(){
        ArrayList<StringBuilder> attributes = new ArrayList<>();

        attributes.add(new StringBuilder("Name: " + getName()));

        attributes.add(new StringBuilder("Level: " + getLevel()));

        attributes.add(new StringBuilder("EXP: " + getExp()));

        attributes.add(new StringBuilder("Money: " + getMoney()));

        attributes.add(new StringBuilder("HP: " + getHp()));

        attributes.add(new StringBuilder("Mana: " + getMana()));

        attributes.add(new StringBuilder("Strength: " + getStrength()));

        attributes.add(new StringBuilder("Agility: " + getAgility()));

        attributes.add(new StringBuilder("Dexterity: " + getDexterity()));

        if(weapon!=null)
            attributes.add(new StringBuilder("Weapon: " + weapon.getName()));

        if(armor!=null)
            attributes.add(new StringBuilder("Armor: " + armor.getName()));

        if(spell!=null)
            attributes.add(new StringBuilder("Spell: " + spell.getName()));

        return attributes;
    }

    public int getMana() {
        return mana;
    }

    private void setMana(int mana) {
        this.mana = mana;
    }

    public int getStrength() {
        return strength;
    }

    protected void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAgility() {
        return agility;
    }

    protected void setAgility(int agility) {
        this.agility = agility;
    }

    public int getDexterity() {
        return dexterity;
    }

    protected void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getMoney() {
        return money;
    }

    private void setMoney(int money) {
        this.money = money;
    }

    public int getExp() {
        return exp;
    }

    private void setExp(int exp) {
        this.exp = exp;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setWeapon(Weapon weapon) {
        if(this.weapon!=null)
            this.weapon.unequip();
        this.weapon = weapon;
        this.weapon.equip();
    }

    public void setArmor(Armor armor) {
        if(this.armor!=null)
            this.armor.unequip();
        this.armor = armor;
        this.armor.equip();
    }

    public void setSpell(Spell spell) {
        if(this.spell!=null)
            this.spell.unequip();
        this.spell = spell;
        this.spell.equip();
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public Spell getSpell() {
        return spell;
    }

    @Override
    public String toString() {
        return getName();
    }

    public static ArrayList<StringBuilder> getGraphicLines(){
        ArrayList<StringBuilder> lines = new ArrayList<>();
        for(String s : Hero.graphic.split("\n")){
            lines.add(new StringBuilder(s));
        }
        return lines;
    }

    private int mana;
    private int strength;
    private int agility;
    private int dexterity;
    private int money;
    private int exp;
    private Weapon weapon;
    private Armor armor;
    private Spell spell;
    private Inventory inventory;
    private static String graphic =
            "            -*%@@@@%*-.       \n"+
            "          :=%@@@@@@@@@@%=     \n"+
            "      +@@@@@@@@@@@@@@@@@@@@*  \n"+
            " :#@@@@@@@@@@@@@@@@@@@@@@@@@@:\n"+
            "#@@@@@@@@@@@@@@@@@@@@@@@@@@@@#\n"+
            "=@@@@@@#-..-=++*@@@@@@@@@@@@@@\n"+
            "  -==-.       +@@@@@@@@@@@@@@#\n"+
            "          :%@@@@@@@@@@@@@@@@% \n"+
            "        @@@@@@@@@@@@@@@@@@+   \n"+
            "      =@@@@@@@@@@@@@@@@@@@=:  \n"+
            "    -#@@@@@@@@@@@@@@@@@@@@@@+ \n"+
            "    %@@@@@@@@@@@@@@@@@@@@@@@@.\n";
}
