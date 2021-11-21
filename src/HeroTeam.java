import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// An abstraction of a team of heroes. Includes the methods that are useful when managing multiple heroes.
public class HeroTeam extends Team{

    private ArrayList<Hero> heroes;
    private Scanner scan;
    //win check
    private String teamStatus;
    //private int heroNum = 3;
    private int highest = 7;
    
    public HeroTeam () {
        scan = new Scanner(System.in);
        heroes = new ArrayList<Hero>();
    }
    

    //add a hero to the team
    public void addHero(Hero h){
    	heroes.add(h);
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
    
    public int getHighest() {
        return this.highest;
    }

    //display the name of heroes in the team
    public void displayHerosName(){
        System.out.print(Displayer.listDisplay(heroes,"Heroes",0));
    }

    public String displayHerosNameString(){
        return Displayer.listDisplay(heroes,"Heroes",0).trim();
    }
    
    public void updateHighest() {
    	for(Hero h : heroes) {
    		if(h.getRow()<this.highest) 
    			this.highest = h.getRow();
    		else
    			continue;
    	}
    }

}
