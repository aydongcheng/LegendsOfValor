import java.util.ArrayList;
import java.util.Random;

//the entity of monster
public class Monster extends Characters{
    public Monster(){}

    //create monster with attributes
    public Monster(String name, int initLevel, int damage, int defense, int dodgeChance){
        super(name);
        setInitLevel(initLevel);
        setLevel(initLevel);
        setDamage(damage);
        setDefense(defense);
        setDodgeChance(dodgeChance);
        setHp(initLevel*100);
        monsterIndexer++;
        
    }

    //create monster with attributes in string list form
    public Monster(String[] attributes){
        this(attributes[0], Integer.parseInt(attributes[1]), Integer.parseInt(attributes[2]),
                Integer.parseInt(attributes[3]), Integer.parseInt(attributes[4]));
    }

    @Override
    public void levelUpTo(int Level) {
        setDamage((int) Math.ceil(damage*Math.pow((1.05),(Level - getInitLevel()))));
        setDefense((int) Math.ceil(defense*Math.pow((1.05),(Level - getInitLevel()))));
        setDodgeChance((int) Math.ceil(dodgeChance*Math.pow((1.05),(Level - getInitLevel()))));
        setLevel(Level);
        setHp(getLevel()*100);
    }

    @Override
    public int attack() {
        return (int) (getDamage()*0.05);
    }

    @Override
    public int getHurt(int damage) {
        Random r = new Random();
        int i = r.nextInt(100);

        if(i <= Math.ceil(getDodgeChance()*0.01))
            return 0;

        int actualDamage = Math.max(damage - ((int) (getDefense()*0.01)), 0);

        if(getHp() <= actualDamage) {
            actualDamage = getHp();
            setFaint(true);
            setHp(0);
            Window.newMessage("Monster "+ getName() +" is dead");
        }
        else
            setHp(getHp()-actualDamage);

        return actualDamage;
    }

    //get the lines used to display monster
    public ArrayList<StringBuilder> getDisplayLines(){
        ArrayList<StringBuilder> attributes = new ArrayList<>();

        attributes.add(new StringBuilder("Name: " + getName()));

        attributes.add(new StringBuilder("Level: " + getLevel()));

        attributes.add(new StringBuilder("HP: " + getHp()));

        attributes.add(new StringBuilder("Damage: " + getDamage()));

        attributes.add(new StringBuilder("Defense: " + getDefense()));

        attributes.add(new StringBuilder("Dodge Chance: " + getDodgeChance()));

        return attributes;
    }

    public void display(){
        Displayer.displayLines(getDisplayLines());
    }

    public int getInitLevel() {
        return initLevel;
    }

    private void setInitLevel(int initLevel) {
        this.initLevel = initLevel;
    }

    public int getDamage() {
        return damage;
    }

    protected void setDamage(int damage) {
        this.damage = damage;
    }

    public int getDefense() {
        return defense;
    }

    protected void setDefense(int defense) {
        this.defense = defense;
    }

    public int getDodgeChance() {
        return dodgeChance;
    }

    protected void setDodgeChance(int dodgeChance) {
        this.dodgeChance = dodgeChance;
    }

    @Override
    public String toString() {
        return getName();
    }
    
    public int getIdx() {
    	return monsterIdx;
    }

    private int initLevel;
    private int damage;
    private int defense;
    private int dodgeChance;
    //To keep track of how many monster have been generated
    private static int monsterIndexer = 0;
    //unique index number for each of the monsters
    private final int monsterIdx = monsterIndexer;
}
