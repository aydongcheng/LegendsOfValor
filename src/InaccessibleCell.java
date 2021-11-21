// The cell that is inaccessible.
public class InaccessibleCell extends Cell {
    public InaccessibleCell() {super();}
    
	public InaccessibleCell(Boolean accessible){
		super();
		this.setAccessible(accessible);
		
	}
    @Override
    public String toString() {
        return "+-------+\n" + 
               "|x|X|X|x|\n" +
               "|x|X|X|x|\n" +
               "+-------+";
    }
}
