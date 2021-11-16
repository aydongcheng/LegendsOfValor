//create certain type of spell
public class SpellCreator implements MerchandiseCreator{
    public SpellCreator(){}

    @Override
    public Merchandise createMercandise(String infoList) {
        String[] data = infoList.split("\\s+");
        switch (data[data.length-1]){
            case "IceSpells":
                return new IceSpell(data);
            case "FireSpells":
                return new FireSpell(data);
            case "LightningSpells":
                return new LightningSpell(data);
            default:
                return null;
        }
    }
}
