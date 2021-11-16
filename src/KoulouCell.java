public class KoulouCell extends SpecialCell{
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