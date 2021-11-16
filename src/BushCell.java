public class BushCell extends SpecialCell{
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