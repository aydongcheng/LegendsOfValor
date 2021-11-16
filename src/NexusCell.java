public class NexusCell extends Cell{
    
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