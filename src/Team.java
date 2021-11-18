import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//the entity of team
public abstract class Team {
	/*TODO remove move location methods, add to Hero class
    private int row;
    private int column;
    */
    private ArrayList<Characters> characters;
    private Scanner scan;
    
    public Team(){
        scan = new Scanner(System.in);
        characters = new ArrayList<>();
        
        //setColumn(0);
        //setRow(0);
    }
    
    //move to new location
    /*
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
    */

	

}
