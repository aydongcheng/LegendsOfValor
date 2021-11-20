public class CaveCell extends SpecialCell{
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
               "|  CAVE |\n" +
               "| " + 
                  slot1 +
                  " " + 
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}