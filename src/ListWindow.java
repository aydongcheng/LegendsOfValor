import java.util.ArrayList;

public class ListWindow extends Window{
    public ListWindow(){}

    public ListWindow(String title){
        super();
        setWidthHeight(94, 18);
        subWidgets.remove(0);
        canvas = new Canvas(widgetWidth, widgetHeight, subWidgets);
        this.title = title;
        TextWidget textWidget = new TextWidget(title.length()+4, 3, new String[]{" "+title});
        textWidget.setPosition(1, widgetWidth/2-textWidget.widgetWidth/2);
        subWidgets.add(textWidget);
    }

    @Override
    public String toString() {
        return canvas.toString();
    }

    public void setPosition(int rowStart, int colStart) {
        super.setPosition(rowStart,colStart);
        canvas.setPosition(rowStart,colStart);
    }

    private String title;
}
