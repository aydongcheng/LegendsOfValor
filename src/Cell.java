//the super class of all cell in the map
public abstract class Cell extends CMLWidget{
    //whether the cell is accessible
    private boolean isAccessible = true;
    //the type of the cell
    private String type;

    protected String slot1 = "  ";
    protected String slot2 = "  ";

    protected Hero theHero = null;
    protected Monster theMonster = null;


    public Cell() {
        setWidthHeight(9, 4);
    };

    public Cell(String type, boolean accessible) {
        this();

        setType(type);
        setAccessible(accessible);
    };

    private void setAccessible(boolean accessible) {
        isAccessible = accessible;
    }

    private void setType(String type) {
        this.type = type;
    }

    public boolean isAccessible() {
        return isAccessible;
    }

    public String getType() {
        return type;
    }

    public abstract String toString();

    public void arrive(Characters c, int id, boolean isHero) {
        String slotTmp;

        if (isHero) {
            slotTmp = "H" + (id+1);
            theHero = (Hero)c;
        }
        else {
            slotTmp = "M" + (id+1);
            theMonster = (Monster)c;
        }

        if (slot1.equals("  ")) {
            slot1 = slotTmp;
        }
        else {
            slot2 = slotTmp;
        }
    }

    public void leave(Characters c, int id, boolean isHero) {
        if (isHero) {
            theHero = null;
            if (slot1.equals("H" + (id+1))) {
                slot1 = "  ";
            }
            else {
                slot2 = "  ";
            }
        }
        else {
            theMonster = null;
            if (slot1.equals("M" + (id+1))) {
                slot1 = "  ";
            }
            else {
                slot2 = "  ";
            }
        }
    }

    public boolean hasHero() {
        return theHero != null;
    }

    public boolean hasMonster() {
        return theMonster != null;
    }

    public Hero getHero() {
        return theHero;
    }

    public Monster getMonster() {
        return theMonster;
    }
}
