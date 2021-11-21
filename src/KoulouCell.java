// A special cell modifing hero status
public class KoulouCell extends SpecialCell {
    public KoulouCell() {super();}

    @Override
    public void arrive(Characters c, int id, boolean isHero) {
        super.arrive(c, id, isHero);
        if (isHero) {
            theHero.setStrength((int)Math.ceil(theHero.getStrength() * 1.1));
        }
        
    }

    @Override
    public void leave(Characters c, int id, boolean isHero) {
        if (isHero) {
            theHero.setStrength((int)Math.ceil(theHero.getStrength() / 1.1));
        }
        super.leave(c, id, isHero);
    }

    @Override
    public String toString() {
        return "+-------+\n" + 
               "| KOULOU|\n" +
               "| " + 
                  slot1 +
                  " " + 
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}