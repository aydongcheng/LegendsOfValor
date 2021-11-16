import java.util.ArrayList;
import java.util.Scanner;

//the tool class to display infos
public class Displayer {
    public Displayer(){}

    //diaplay the data in form layout
    public static String formDisplay(ArrayList<ArrayList<StringBuilder>> items, int lineLength, int itemLength){
        StringBuilder stringBuilder = new StringBuilder();
        //divide the items to several rows
        for(int row = 0; row < Math.ceil((double) items.size()/lineLength); row++) {
//            stringBuilder.append("\n");
            int line = 0;
            boolean label = true;
            int lineSize = lineLength;
            if(row ==  Math.ceil((double) items.size()/lineLength)-1)
                lineSize = items.size()%lineLength == 0? lineLength:items.size()%lineLength;
            while (label){
                label = false;
                for(int i = 0; i< lineLength; i++){
                    if(lineSize > i) {
                        if (line < items.get(row * lineLength + i).size()) {
                            StringBuilder item = items.get(row * lineLength + i).get(line);
                            stringBuilder.append(items.get(row * lineLength + i).get(line));
                            stringBuilder.append(" ".repeat(Math.max(0, itemLength - item.length())));
                            label = true;
                        } else
                            stringBuilder.append(" ".repeat(itemLength));
                    }
                    else
                        stringBuilder.append(" ".repeat(itemLength));
                }
                stringBuilder.append("\n");
                line++;
            }
        }
        return stringBuilder.toString();
    }

    //display the list with index
    public static <T> void listDisplay(ArrayList<T> items, String itemName, int startIndex, int lineLength, int itemLength){
        int index = startIndex;
        StringBuilder s = new StringBuilder(itemName+" :");
        if(s.length()<itemLength)
            s.append(" ".repeat(Math.max(0, itemLength - s.length())));
        else
            itemLength = s.length();
        StringBuilder stringBuilder = new StringBuilder(s);
        for(T t : items){
            if((index-startIndex) % lineLength == 0 && (index-startIndex)!=0) {
                stringBuilder.append("\n");
                stringBuilder.append(" ".repeat(itemLength));
            }
            s = new StringBuilder(index + "."+t.toString());
            if(s.length()<itemLength)
                s.append(" ".repeat(Math.max(0, itemLength - s.length())));
            stringBuilder.append(s);
            index++;
        }
        System.out.println(stringBuilder);
    }

    //display the list with fixed lineLength and itemLength
    public static <T> void listDisplay(ArrayList<T> items, String itemName, int startIndex){
        listDisplay(items,itemName,startIndex,3,30);
    }

    //ask the client to choose the one of the item in the list
    public static int chooseList(int listSize){
        while (true) {
            System.out.println("Please make your choice:(input the number in front of the item)");
            String input = Displayer.scan.next();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Sorry, your input is illegal! Please try again.");
                continue;
            }
            if (choice < 0 || choice > listSize-1)
                System.out.println("Sorry, your input is illegal! Please try again.");
            else
                return choice;
        }
    }

    //display the lines
    public static void displayLines(ArrayList<StringBuilder> attributes){
        for(StringBuilder stringBuilder: attributes){
            System.out.println(stringBuilder);
        }
    }

    private static Scanner scan = new Scanner(System.in);
}
