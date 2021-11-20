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
