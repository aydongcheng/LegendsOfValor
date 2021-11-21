import java.util.ArrayList;

// An abstraction of a team of monsters. Includes the methods that are useful when managing multiple monsters.
public class MonsterTeam extends Team{

    private ArrayList<Monster> Monsters;
    //win check
    // private String teamStatus;

    public MonsterTeam () {
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

    public String displayMonstersNameString(){
        return Displayer.listDisplay(Monsters,"Monsters",0).trim();
    }
}
