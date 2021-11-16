import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//the entity of team
public abstract class Team {
    public Team(){ }

    //add a hero to the team
    protected abstract void addCharacters(Characters h);
}
