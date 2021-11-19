import java.util.ArrayList;
import java.util.Scanner;

public class MonsterTeam extends Team{

    private ArrayList<Monster> Monsters;
    private Scanner scan;
    //win check
    private String teamStatus;
    
    public MonsterTeam () {
        scan = new Scanner(System.in);
        Monsters = new ArrayList<>();
        
        //TODO better build monster team method
        for (int i=0;i<3;i++) {
        	Monster m = RandomMonsterCreator.createMonster(1);
            m.setRow(0);
            m.setColumn(i);
        	this.addMonster(m);
        }
        
    }
    

    //add a monster to the team
    private void addMonster(Monster m){
    	Monsters.add(m);
    }


    public ArrayList<Monster> getMonsters() {
        return Monsters;
    }

    //display the name of heroes in the team
    public void displayMonstersName(){
        System.out.print(Displayer.listDisplay(Monsters,"Monsters",0));
    }
}
