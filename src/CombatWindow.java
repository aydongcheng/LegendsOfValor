// The combat window. Includes a game map and a manual.
public class CombatWindow extends Window {
    private LVBoard theBoard;

    public CombatWindow() {
        theBoard = new LVBoard(8);
        theBoard.setPosition(1, 10);
        subWidgets.add(theBoard);

        // Add manual
        String[] messages = {"  +----------+",
                             "  |  Manual  |",
                             "  +----------+",
                             "----------------",
                             "Please choose:",
                             "1. Move",
                             "2. Hero Info",
                             "3. Buy",
                             "4. Attack",
                             "5. Cast Spell",
                             "6. Teleport",
                             "7. Back to Nexus",
                             "8. End Turn",
                             "9. Quit Game"};
        TextWidget textWidget = new TextWidget(18, 37, messages);
        textWidget.setPosition(1, 95);
        subWidgets.add(textWidget);

        // Add Row Number
        String[] messagesRowNumber = {"", "",
                                     "Row #:",
                                      "",
                                      " Row 1",
                                      "", "", "",
                                      " Row 2",
                                      "", "", "",
                                      " Row 3",
                                      "", "", "",
                                      " Row 4",
                                      "", "", "",
                                      " Row 5",
                                      "", "", "",
                                      " Row 6",
                                      "", "", "",
                                      " Row 7",
                                      "", "", "",
                                      " Row 8"};
        TextWidget textWidgetRowNumber = new TextWidget(8, 37, messagesRowNumber);
        textWidgetRowNumber.setPosition(1, 1);
        subWidgets.add(textWidgetRowNumber);
    }

    public LVBoard getTheBoard() {
        return theBoard;
    }
}
