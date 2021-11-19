//the super class of all cell in the map
public abstract class Cell extends CMLWidget{
    //whether the cell is accessible
    private boolean isAccessible = true;
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
    
    /*
     *@param  i the index of this arriving hero
     * use hero's index as his/hers serial number
     */
    public void slot1Arrive(int i) {
        slot1 = "H" + (i+1);
    }

    public void slot2Arrive(int i) {
        slot2 = "H" + (i+1);
    }   
}
