import java.util.ArrayList;

public class HeroTeam extends Team{
    public HeroTeam(){
        heros = new ArrayList<>();
    }

    @Override
    protected void addCharacters(Characters h) {
        heros.add((Hero) h);
    }

    public ArrayList<Hero> getHeros() {
        return heros;
    }

    private ArrayList<Hero> heros;
}
