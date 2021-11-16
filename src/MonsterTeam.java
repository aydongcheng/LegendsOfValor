import java.util.ArrayList;

public class MonsterTeam extends Team{
    public MonsterTeam(){
        monsters = new ArrayList<>();
    }

    @Override
    protected void addCharacters(Characters h) {
        monsters.add((Monster) h);
    }

    public ArrayList<Monster> getMonsters() {
        return monsters;
    }

    private ArrayList<Monster> monsters;
}
