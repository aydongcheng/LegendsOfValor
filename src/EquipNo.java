// A class which displays whether an item is equipable
public class EquipNo implements Equipable {
    public EquipNo(){}

    @Override
    public void equip() {
        Window.newMessage("This is not equipable");
    }

    @Override
    public void unequip() {
        Window.newMessage("This is not equipable");
    }
}
