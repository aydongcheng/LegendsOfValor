public class InfoWindow extends Window{
    public InfoWindow(){
        super();
        setWidthHeight(94+20, 42);
        canvas = new Canvas(widgetWidth, widgetHeight, subWidgets);
        messages = new String[]{"  +----------+",
                "  |  Manual  |",
                "  +----------+",
                "----------------",
                "    b: Buy",
                "    s: Sell",
                "    e: Exit",
                "Manual",
                "Manual",
                "Manual",
                "Manual",
                "Manual",};
        TextWidget textWidget = new TextWidget(18, 37, messages);
        textWidget.setPosition(1, 95);
        subWidgets.add(textWidget);
    }

    private String[] messages;
}
