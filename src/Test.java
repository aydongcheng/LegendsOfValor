public class Test {
    public static void main(String[] args) {
        Window welcomeWindow = new WelcomeWindow();
        for (int idx = 0; idx < 3; idx++) {
            welcomeWindow.newMessage("Welcome!");
        }
        System.out.println(welcomeWindow);

        Utils.safeIntInput("Input:", 0, 0);

        Utils.beautifulWait(0.15, 94);
        Utils.beautifulWait(0.15, 94);

        Window combatWindow = new CombatWindow();
        for (int idx = 0; idx < 3; idx++) {
            combatWindow.newMessage("Map!");
        }
        System.out.println(combatWindow);

        Utils.safeIntInput("Input:", 0, 0);

        Utils.beautifulWait(0.15, 94);
        Utils.beautifulWait(0.15, 94);

        LVBoard theBoard = ((CombatWindow)combatWindow).getTheBoard();  // temporary; for testing only
        theBoard.cells[7][0].slot1Arrive();
        theBoard.cells[7][0].slot2Arrive();  // Note: modify slot1Arrive and slot2Arrive s.t. they need Character as inputs
        for (int idx = 0; idx < 3; idx++) {
            combatWindow.newMessage("Heroes arrived!");
        }
        System.out.println(combatWindow);

        Utils.safeIntInput("Input:", 0, 0);

        Utils.beautifulWait(0.15, 94);
        Utils.beautifulWait(0.15, 94);

        Window exitWindow = new ExitWindow();
        for (int idx = 0; idx < 8; idx++) {
            exitWindow.newMessage("Goodbye!");
        }
        System.out.println(exitWindow);
    }
}
