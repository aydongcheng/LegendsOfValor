public class CombatWindow extends Window {
    private LVBoard theBoard;
    
    public CombatWindow() {
        theBoard = new LVBoard(8);
        theBoard.setPosition(1, 1);
        subWidgets.add(theBoard);

        String[] messages = {"  +----------+",
                             "  |  Manual  |",
                             "  +----------+",
                             "----------------",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",
                             "Manual",};
        TextWidget textWidget = new TextWidget(18, 37, messages);
        textWidget.setPosition(1, 75);
        subWidgets.add(textWidget);        
    }

    public LVBoard getTheBoard() {
        return theBoard;
    }
}
