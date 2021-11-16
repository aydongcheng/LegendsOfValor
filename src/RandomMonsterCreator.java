import java.util.List;
import java.util.Random;

//random choose a kind of monster and create
public class RandomMonsterCreator implements MonsterCreator{
    public RandomMonsterCreator(){}

    @Override
    public Monster createMonster() {
        return createMonster(-1);
    }

    public Monster createMonster(int level){
        Random r = new Random();
        int i = r.nextInt(RandomMonsterCreator.fileName.length);
        List<String> lines = new FileReader().readFile(RandomMonsterCreator.fileName[i]);
        lines.remove(0);
        lines.removeIf(o -> o.equals(""));
        if(level>0)
            lines.removeIf(o -> Integer.parseInt(o.split("\\s+")[1])>level);
        int i2 = 0;
        if(lines.size()>1)
            i2 = r.nextInt(lines.size()-1);
        Monster monster;
        switch (i){
            case 0:
                monster = new Dragon(lines.get(i2).split("\\s+"));break;
            case 1:
                monster = new Exoskeleton(lines.get(i2).split("\\s+"));break;
            default:
                monster = new Spirits(lines.get(i2).split("\\s+"));break;
        }
        if(level>monster.getInitLevel())
            monster.levelUpTo(level);
        return monster;
    }

    private static final String[] fileName = new String[]{"Dragons", "Exoskeletons", "Spirits"};
}
