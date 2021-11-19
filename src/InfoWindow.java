public class InfoWindow extends Window{
    public InfoWindow(String infoWindowName){
        super();
        setWidthHeight(94+20, 37+17);
        canvas = new Canvas(widgetWidth, widgetHeight, subWidgets);
        this.messagesSetter(infoWindowName);
        TextWidget textWidget = new TextWidget(18, 37, messages);
        textWidget.setPosition(1, 95);
        subWidgets.add(textWidget);
    }

    //a method to customize messages for the Manual section
    private void messagesSetter(String infoWindowName) {
    	switch(infoWindowName) {
    	case "market":
            this.messages = new String[]{"  +----------+",
                    "  |  Manual  |",
                    "  +----------+",
                    "----------------",
                    "    b: Buy",
                    "    s: Sell",
                    "    e: Exit",};
            break;
        /*not sure
    	case "map":
            this.messages = new String[]{"  +----------+",
                    "  |  Manual  |",
                    "  +----------+",
                    "----------------",
                    " 1.Move",
                    " 2.Check Status",
                    " 3.Buy",
                    " 4.Check Map",
                    " 5.Attack",
                    " 6.Cast Spell",
                    " 7.Teleport",
                    " 8.BackTo Nexus",
                    " 9.End Turn",
                    " 0.Quit Game",};
            break;
            */
    	case "heroes":
            this.messages = new String[]{"  +----------+",
                    "  |  Manual  |",
                    "  +----------+",
                    "----------------",
                    "  #: choose hero",
                    "",
                    "  # represents",
                    "  the index",
                    "  of each Hero",};
    		break;
    	case "status":
            this.messages = new String[]{"  +----------+",
                    "  |  Manual  |",
                    "  +----------+",
                    "----------------",
                    "  u: Use/Equip",
                    "  e: Exit",};
    		break;
    	}
    }
    private String[] messages;
}
