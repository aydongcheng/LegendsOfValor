import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private void addHero(Hero h){
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
        Displayer.listDisplay(heroes,"Heroes",0);
    }
    
    public void updateHighest() {
    	for(Hero h : heroes) {
    		if(h.getRow()>this.highest) 
    			this.highest = h.getRow();
    		else
    			continue;
    	}
    }

    //TODO choose hero to join the team
    public void chooseHero(){
    	ArrayList<Hero> heroesList = new ArrayList<>();
        String[] files = new String[]{"Warriors", "Sorcerers", "Paladins"};
        for(String file: files) {
            List<String> lines = new FileReader().readFile(file);
            for(int i = 1;i<lines.size();i++){
                if(lines.get(i).equals(""))
                    break;
                if(file.equals("Warriors"))
                    heroesList.add(new Warrior(lines.get(i).split("\\s+")));
                else if(file.equals("Sorcerers"))
                    heroesList.add(new Sorcerer(lines.get(i).split("\\s+")));
                else
                    heroesList.add(new Paladin(lines.get(i).split("\\s+")));
            }
        }
        Displayer.listDisplay(heroesList,"Heros",0);
        //the required number of heroes in one team is 3
        int HeroNum = 3;
        for(int i=0; i<HeroNum; i++){
            System.out.println("Please select a hero you are interested in.");
            int index = Displayer.chooseList(heroesList.size());
            if(this.heroes.contains(heroesList.get(index))) {
                System.out.println("Hero " + heroesList.get(index).getName() +
                        " is already in the team, please select other heroes.");
                i--;
                Displayer.listDisplay(heroesList,"Heros",0);
            }
            else {
            	heroesList.get(index).display();
                System.out.println("Do you want hero "+ heroesList.get(index).getName() + " to join your team?(y/others)");
                //TODO remove the one player choosed from heroesList
                String input = scan.next();
                if(input.equals("y"))
                    this.addHero(heroesList.get(index));
                else
                    i--;
            }
        }
    }
}
