import java.util.*;

public class Canvas extends CMLWidget {
    private ArrayList<CMLWidget> subWidgets;

    public Canvas(ArrayList<CMLWidget> subWidgets){
        this.subWidgets = subWidgets;
    }

    public Canvas(int width, int height, ArrayList<CMLWidget> subWidgets) {
        setWidthHeight(width, height);
        this.subWidgets = subWidgets;
    }

    public String toString() {
        String outString = "";
        for (int idx_1 = 0; idx_1 < widgetHeight; idx_1++) {
            secondLoop:
            for (int idx_2 = 0; idx_2 < widgetWidth; idx_2++) {
                for (int idx_3 = 0; idx_3 < subWidgets.size(); idx_3++) {
                    // draw frame
                    boolean cond1 = idx_1 % (widgetHeight - 1) == 0;
                    boolean cond2 = idx_2 % (widgetWidth - 1) == 0;
                    if (cond1 && cond2) {
                        outString += "+";
                        continue secondLoop;
                    }

                    if (cond1) {
                        outString += "-";
                        continue secondLoop;
                    }

                    if (cond2) {
                        outString += "|";
                        continue secondLoop;
                    }

                    // draw sub-widgets
                    CMLWidget currentWidget;
                    currentWidget = subWidgets.get(idx_3);

                    if (currentWidget.isWithinRange(idx_1, idx_2)) {
                        if (currentWidget.isStartPoint(idx_2)) {
                            outString += currentWidget.nextLineWithLoop();
                            continue secondLoop;
                        }
                        continue secondLoop;
                    }
                }
                outString += " ";
            }
            outString += "\n";
        }
        return outString;
    }
}



