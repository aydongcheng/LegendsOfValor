import java.util.*;

//the entity of map
public class LVBoard extends Board {
    public LVBoard(){}

    private ProbabilityLVCellCreator probabilityCellCreator;
    private static Map<String, Double> probability = new HashMap<String, Double>();

    private Canvas canvas;
    ArrayList<CMLWidget> subWidgets = new ArrayList<CMLWidget>();;

    //create map with size
    public LVBoard(int size){
        super(size);
        LVBoard.probability.put("plain", 0.25);
        LVBoard.probability.put("bush", 0.25);
        LVBoard.probability.put("cave", 0.25);
        LVBoard.probability.put("koulou", 0.25);

        probabilityCellCreator = new ProbabilityLVCellCreator(LVBoard.probability);
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if ((j == 2) || (j == 5)) {
                    cells[i][j] = new InaccessibleCell(false);
                }
                else if ((i == 0) || (i == size - 1)) {
                    cells[i][j] = new NexusCell();
                }
                else {
                    cells[i][j] = probabilityCellCreator.createCell();
                }


                CMLWidget currentWidget = cells[i][j];
                currentWidget.setPosition(4 + i * 4, 1 + j * 9);
                subWidgets.add(currentWidget);
            }
        }

        // add text widget
        String[] messages = {" Battle Field"};
        TextWidget textWidget = new TextWidget(16, 3, messages);
        textWidget.setPosition(1, 29);
        subWidgets.add(textWidget);

        // create canvas
        canvas = new Canvas(74, 37, subWidgets);

        setWidthHeight(74, 37);
    }

    public int getSize(){
        return getWidth();
    }

    public String toString() {
        return canvas.toString();
    }
}
