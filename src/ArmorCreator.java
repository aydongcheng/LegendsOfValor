//the creator of armor
public class ArmorCreator implements MerchandiseCreator{
    public ArmorCreator(){}

    @Override
    public Merchandise createMercandise(String infoList) {
        return new Armor(infoList.split("\\s+"));
    }
}
