public class InaccessibleCell extends Cell {
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
