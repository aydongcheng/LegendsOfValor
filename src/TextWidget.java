public class TextWidget extends CMLWidget {
    private String[] messages;  
    
    public TextWidget() {}

    public TextWidget(int widgetWidth, int widgetHeight, String[] messages) {
        super.widgetWidth = widgetWidth;
        super.widgetHeight = widgetHeight;
        this.messages = messages;
    }

    public String toString() {
        String outString = "";
        for (int idx_1 = 0; idx_1 < widgetHeight; idx_1++) {
            secondLoop:
            for (int idx_2 = 0; idx_2 < widgetWidth; idx_2++) {
                boolean condition_1 = idx_1 % (widgetHeight - 1) == 0;
                boolean condition_2 = idx_2 % (widgetWidth - 1) == 0;

                if (condition_1 && condition_2) {
                    outString += "+";
                    continue;
                }

                if (condition_1) {
                    outString += "-";
                    continue;
                }

                if (condition_2) {
                    outString += "|";
                    continue;
                }

                int counter = 0;
                for (int idx = 0; idx < messages.length; idx++) {
                    // range for messages1
                    boolean condition_1_tmp = (idx_1 >= 1 + counter) && (idx_1 < 2 + counter);
                    counter += 1;
                    boolean condition_2_tmp = (idx_2 >= 1) && (idx_2 < 1 + messages[idx].length());
                    // start point for map
                    boolean condition_3_tmp = idx_2 == 1;
                
                    if (condition_1_tmp && condition_2_tmp) {
                        if (condition_3_tmp) {
                            outString += messages[idx];
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
