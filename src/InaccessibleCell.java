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
               ""+"\u001b[31m"+"|x|X|X|x|"+"\u001b[0m"+"\n" +
               ""+"\u001b[31m"+"|x|X|X|x|"+"\u001b[0m"+"\n" +
               "+-------+";
    }
}
