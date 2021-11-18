//the super class of characters in the game
public abstract class Characters implements Fightable, LevelUp{
    public Characters(){}

    //create character with name
    public Characters(String name){
        setName(name);
        setFaint(false);
    }

    public String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    protected void setLevel(int level) {
        this.level = level;
    }

    public int getHp() {
        return hp;
    }

    protected void setHp(int hp) {
        this.hp = hp;
    }

    public boolean isFaint() {
        return isFaint;
    }

    protected void setFaint(boolean faint) {
        isFaint = faint;
    }

    public int getRow() {
        return row;
    }

    protected void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    protected void setColumn(int column) {
        this.column = column;
    }

    protected void move(int row, int column){
        setRow(row);
        setColumn(column);
    }

    private int row;
    private int column;
    private String name;
    private int hp;
    private int level;
    private boolean isFaint;
}
