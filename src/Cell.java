//the super class of all cell in the map
public abstract class Cell extends CMLWidget{
    //whether the cell is accessible
    private boolean isAccessible;
    //the type of the cell
    private String type;

    protected String slot1 = "  ";
    protected String slot2 = "  ";

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

    public void slot1Leave() {
        slot1 = "  ";
    }

    public void slot2Leave() {
        slot2 = "  ";
    }   

    public void slot1Arrive() {
        slot1 = "H1";
    }

    public void slot2Arrive() {
        slot2 = "H1";
    }   
}
