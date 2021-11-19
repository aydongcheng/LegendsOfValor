public class KoulouCell extends SpecialCell{
    @Override
    public void slot1Arrive(int i, Hero h) {
        super.slot1Arrive(i, h);
        h.setStrength((int)Math.ceil(h.getStrength() * 1.1));
    }

    @Override
    public void slot2Arrive(int i, Hero h) {
        super.slot1Arrive(i, h);
        h.setStrength((int)Math.ceil(h.getStrength() * 1.1));
    }

    @Override
    public void slot1Leave(Hero h) {
        super.slot1Leave(h);
        h.setStrength((int)Math.ceil(h.getStrength() / 1.1));
    }

    @Override
    public void slot2Leave(Hero h) {
        super.slot2Leave(h);
        h.setStrength((int)Math.ceil(h.getStrength() / 1.1));
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