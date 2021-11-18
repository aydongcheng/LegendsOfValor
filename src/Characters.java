//the super class of characters in the game
public abstract class Characters implements Fightable, LevelUp{
    public Characters(){}

    private String name;
    private int hp;
    private int level;
    private boolean isFaint;
    
    private int row;
    private int column;
    
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
    
    //location related methods are below
    public void move(int row, int column){
        setColumn(column);
        setRow(row);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

}
