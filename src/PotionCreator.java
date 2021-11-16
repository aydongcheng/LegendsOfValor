import java.util.Arrays;
import java.util.List;

//the creator of potion
public class PotionCreator implements MerchandiseCreator{
    public PotionCreator(){}

    @Override
    public Merchandise createMercandise(String infoList) {
        List<String> data = Arrays.asList(infoList.split("\\s+"));
        Potion potion = new Potion(data.get(0),Integer.parseInt(data.get(1)),
                Integer.parseInt(data.get(2)),Integer.parseInt(data.get(3)), data.get(4).split("/"));
        return potion;
    }
}
