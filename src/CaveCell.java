public class CaveCell extends SpecialCell{
    @Override
    public void slot1Arrive(int i, Hero h) {
        super.slot1Arrive(i, h);
        h.setAgility((int)Math.ceil(h.getAgility() * 1.1));
    }

    @Override
    public void slot2Arrive(int i, Hero h) {
        super.slot1Arrive(i, h);
        h.setAgility((int)Math.ceil(h.getAgility() * 1.1));
    }

    @Override
    public void slot1Leave(Hero h) {
        super.slot1Leave(h);
        h.setAgility((int)Math.ceil(h.getAgility() / 1.1));
    }

    @Override
    public void slot2Leave(Hero h) {
        super.slot2Leave(h);
        h.setAgility((int)Math.ceil(h.getAgility() / 1.1));
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