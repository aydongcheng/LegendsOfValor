public class WelcomeWindow extends Window {

    public WelcomeWindow() {
        String[] messages = {"", "", "",
        "                            " + "--------------------------------",
        "                                    " + "Legends of Valor", 
        "                            " + "--------------------------------",
        "",
        "",
        "---------------",
        "Basic Controls:",
        "---------------",
        "**insert new instructions/greetings here**",
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
        "          " + ">> Press Enter key to select your heroes and begin the journey! <<"};
        TextWidget textWidget = new TextWidget(92, 37, messages);
        textWidget.setPosition(1, 1);
        subWidgets.add(textWidget);        
    }
}
