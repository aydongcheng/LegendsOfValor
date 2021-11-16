import java.util.Map;

//the game monster and hero 's map's cell creator
public class ProbabilityLVCellCreator extends ProbabilityCellCreator{
    public ProbabilityLVCellCreator(){}

    public ProbabilityLVCellCreator(Map<String ,Double> probability){
        super(probability);
    }

    //create cell with certain type;
    @Override
    public Cell createCell() {
        String type = selectType();
        Cell cell;
        switch (type){
            case "plain":
                cell = new PlainCell();
                break; 
            case "bush":
                cell = new BushCell();
                break;
            case "cave":
                cell = new CaveCell();
                break;
            case "koulou":
                cell = new KoulouCell();
                break;                
            default:
                cell = null;
        }
        return cell;
    }
}
