import java.util.*;

public abstract class Window extends CMLWidget{
    protected ArrayList<CMLWidget> subWidgets = new ArrayList<CMLWidget>();
    protected Canvas canvas;

    private static LogViewWidget logViewWidget = new LogViewWidget(92, 15);
    private static int logCounter = 0;

    public Window() {
        setWidthHeight(74 + 20, 37 + 17);
        canvas = new Canvas(widgetWidth, widgetHeight, subWidgets);

        // add log view widget
        logViewWidget.setPosition(38, 1);
        subWidgets.add(logViewWidget);
    };

    public void newMessage(String message) {
        if (logCounter <= 9) {
            logViewWidget.newMessage(" [" + logCounter + "]: " + message);
        }
        else {
            logViewWidget.newMessage("[" + logCounter + "]: " + message);
        }

        logCounter += 1;
    }

    public void addSubWidget(CMLWidget subWidget){
        subWidgets.add(subWidget);
    }

    public String toString() {
        return canvas.toString();
    }
}
