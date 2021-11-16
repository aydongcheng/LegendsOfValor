import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

//create different cells with probability
public abstract class ProbabilityCellCreator implements CellCreator{
    public ProbabilityCellCreator(){}

    public ProbabilityCellCreator(Map<String ,Double> probability){
        this.probability = probability;
    }

    //according to the probability to determine the type of cell to create
    protected String selectType(){
        Random r = new Random();
        int i = r.nextInt(100);
        int count = 0;
        int addUpProb = 0;
        for(Double prob : probability.values()){
            if(i + 1 <= addUpProb + prob * 100)
                return new ArrayList<String>(probability.keySet()).get(count);
            else {
                addUpProb += prob * 100;
                count++;
            }
        }
        return new ArrayList<String>(probability.keySet()).get(probability.keySet().size()-1);
    }

    private Map<String ,Double> probability;
}
