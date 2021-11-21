// The first window that welcomes the player.
public class WelcomeWindow extends Window {

    public WelcomeWindow() {
        String[] messages = {"", "", "",
        "                                       " + "--------------------------------",
        "                                               " + "Legends of Valor",
        "                                       " + "--------------------------------",
        "",
        "",
        "---------------",
        "Basic Controls:",
        "---------------",
        "  Use numbers and letters to navigate and select actions!",
        /*
        "  w: Moving Up" + "       e: Interact",
        "  s: Moving Down" + "     c: Inventory",
        "  a: Moving Left" + "     i: Info",
        "  d: Moving Right" + "    q: Leave the Game",
        */
        "",
        "",
        "------------",
        "Please Note:",
        "------------",
        "  Zoom and adjust the terminal so the UI displays correctly. :)",
        "",
        "",
        "",
        "",
        "",
        "",
        "                     " + ">> Press Enter key to select your heroes and begin the journey! <<"};
        TextWidget textWidget = new TextWidget(92+20, 37, messages);
        textWidget.setPosition(1, 1);
        subWidgets.add(textWidget);
    }
}
