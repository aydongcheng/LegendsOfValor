// A normal cell.
public class PlainCell extends Cell {
    public PlainCell () {super();}
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
