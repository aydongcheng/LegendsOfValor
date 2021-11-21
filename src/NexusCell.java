// The cell for nexus
public class NexusCell extends Cell{
    public NexusCell() {super();}

    @Override
    public String toString() {
        return "+-------+\n" +
               "| "+"\u001b[35m"+"NEXUS"+"\u001b[0m"+" |\n" +
               "| " +
                  slot1 +
                  " " +
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}
