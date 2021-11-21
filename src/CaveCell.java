// A special cell, which modifies the hero inside it
public class CaveCell extends SpecialCell{
    public CaveCell() {super();}

    @Override
    public void arrive(Characters c, int id, boolean isHero) {
        super.arrive(c, id, isHero);
        if (isHero) {
            theHero.setAgility((int)Math.ceil(theHero.getAgility() * 1.1));
        }

    }

    @Override
    public void leave(Characters c, int id, boolean isHero) {
        if (isHero) {
            theHero.setAgility((int)Math.ceil(theHero.getAgility() / 1.1));
        }
        super.leave(c, id, isHero);
    }

    @Override
    public String toString() {
        return "+-------+\n" +
                "|  "+"\u001b[33m"+"CAVE"+"\u001b[30m" + " |\n" +
               "| " +
                  slot1 +
                  " " +
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}
