public class PlainCell extends Cell {
    @Override
    public String toString() {
        return "+-------+\n" + 
               "| PLAIN |\n" +
               "| " + 
                  slot1 +
                  " " + 
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}
