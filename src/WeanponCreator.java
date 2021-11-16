//the creator of weapons
public class WeanponCreator implements MerchandiseCreator{
    public WeanponCreator(){}

    @Override
    public Merchandise createMercandise(String infoList) {
        return new Weapon(infoList.split("\\s+"));
    }
}
