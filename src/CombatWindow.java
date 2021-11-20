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
        textWidget.setPosition(1, 75);
        subWidgets.add(textWidget);        
    }

    public LVBoard getTheBoard() {
        return theBoard;
    }
}
