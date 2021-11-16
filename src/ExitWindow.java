public class ExitWindow extends Window {

    public ExitWindow() {
        String[] messages = {"", "", "",
        "                            " + "--------------------------------",
        "                                    " + "Legends of Valor", 
        "                            " + "--------------------------------",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "                                      " + "------------",
        "                                      " + "  Goodbye!",
        "                                      " + "------------"};
        TextWidget textWidget = new TextWidget(92, 37, messages);
        textWidget.setPosition(1, 1);
        subWidgets.add(textWidget);        
    }
}
