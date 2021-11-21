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
    }
}
