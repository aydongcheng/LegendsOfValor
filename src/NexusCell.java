// The cell for nexus
public class NexusCell extends Cell{
    public NexusCell() {super();}

    @Override
    public String toString() {
        return "+-------+\n" +
               "| "+"\033[35m"+"NEXUS"+"\033[0m"+" |\n" +
               "| " +
                  slot1 +
                  " " +
                  slot2 +
                      " |\n" +
               "+-------+";
    }
}
