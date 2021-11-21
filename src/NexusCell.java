// The cell for nexus
public class NexusCell extends Cell{
    public NexusCell() {super();}
    
    @Override
    public String toString() {
        return "+-------+\n" + 
               "| NEXUS |\n" +
               "| " + 
                  slot1 +
                  " " + 
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}