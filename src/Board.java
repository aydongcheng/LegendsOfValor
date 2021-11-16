//the super class of entity board
public class Board extends CMLWidget{
    private int width;
    private int length;
    protected Cell[][] cells;

    public Board(){}

    //create board with width and length
    public Board(int width, int length){
        setLength(length);
        setWidth(width);
        cells = new Cell[length][width];
    }

    //create a squared board with size
    public Board(int size){
        this(size,size);
    }

    private void setWidth(int width) {
        this.width = width;
    }

    private void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }

    //the grid in the map
    public Cell[][] getCells() {
        return cells;
    }

    public String toString() {
        return "";
    }
}
