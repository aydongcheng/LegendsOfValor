// A special cell. Modifies the attributes of heroes.
public class BushCell extends SpecialCell{
    public BushCell() {super();}

    @Override
    public void arrive(Characters c, int id, boolean isHero) {
        super.arrive(c, id, isHero);
        if (isHero) {
            theHero.setDexterity((int)Math.ceil(theHero.getDexterity() * 1.1));
        }

    }

    @Override
    public void leave(Characters c, int id, boolean isHero) {
        if (isHero) {
            theHero.setDexterity((int)Math.ceil(theHero.getDexterity() / 1.1));
        }
        super.leave(c, id, isHero);
    }

    @Override
    public String toString() {
        return "+-------+\n" +
               "|  "+"\033[32m"+"BUSH"+"\033[0m"+" |\n" +
               "| " +
                  slot1 +
                  " " +
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}
