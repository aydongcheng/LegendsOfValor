// A class for equiping items
public class EquipOnce implements Equipable{
    public EquipOnce(){}

    public EquipOnce(Merchandise merchandise){
        this.merchandise = merchandise;
        merchandise.setEquipped(false);
    }

    @Override
    public void equip() {
        merchandise.setEquipped(true);
    }

    @Override
    public void unequip() {
        merchandise.setEquipped(false);
    }

    private Merchandise merchandise;
}
