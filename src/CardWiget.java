import java.util.ArrayList;

// A CMLWidget used to demonstrate info in an arranged way.
public class CardWiget extends CMLWidget{
    public CardWiget(){ }

    public CardWiget(ArrayList<ArrayList<StringBuilder>> characters){
        this.characters = characters;
        setPosition(1,1);
        setWidthHeight(90, this.toString().split("\n").length);
    }

    @Override
    public String toString() {
        return Displayer.formDisplay(characters,3,30);
    }

    private ArrayList<ArrayList<StringBuilder>> characters;
}
