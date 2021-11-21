import java.util.*;

// A general class for a CMLWidget called a Window. A Window includes a static logView widget and specifies a standard size that's applied across all windows.
public abstract class Window extends CMLWidget{
    protected ArrayList<CMLWidget> subWidgets = new ArrayList<CMLWidget>();
    protected Canvas canvas;

    private static LogViewWidget logViewWidget = new LogViewWidget(92 + 20, 15);
    private static int logCounter = 0;

    private static Window previousWindow;

    public Window() {
        setWidthHeight(94 + 20, 37 + 17);
        canvas = new Canvas(widgetWidth, widgetHeight, subWidgets);

        // add log view widget
        logViewWidget.setPosition(38, 1);
        subWidgets.add(logViewWidget);
    };

    public static void show() {
        if (previousWindow != null) {
            System.out.print(previousWindow);
        }
    }

    public static void newMessage(String message) {
        if (message.equals("__clear__")) {
            logViewWidget.newMessage("__clear__");
            return;
        }

        String[] splitted = message.split("\n");
        for (int idx = 0; idx < splitted.length; idx++) {
            if (logCounter <= 9) {
                logViewWidget.newMessage(" [" + logCounter + "]: " + splitted[idx]);
            }
            else {
                logViewWidget.newMessage("[" + logCounter + "]: " + splitted[idx]);
            }
            logCounter += 1;
        }
    }

    public void addSubWidget(CMLWidget subWidget){
        subWidgets.add(subWidget);
    }

    public String toString() {
        previousWindow = this;
        return canvas.toString();
    }
}
