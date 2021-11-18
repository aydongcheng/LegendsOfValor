public class BlankWidget extends CMLWidget{
    public BlankWidget(){ }

    public BlankWidget(int width, int rowStart, int colStart, String string){
        this.string = string;
        setWidthHeight(width, string.split("\n").length);
        setPosition(rowStart,colStart);
    }

    @Override
    public String toString() {
        return string;
    }

    private String string;
}
