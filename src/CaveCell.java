public class CaveCell extends SpecialCell{
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