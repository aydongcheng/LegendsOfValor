public class BushCell extends SpecialCell{
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
               "|  BUSH |\n" +
               "| " + 
                  slot1 +
                  " " + 
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}