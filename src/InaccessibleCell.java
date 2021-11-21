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
               ""+"\033[31m"+"|x|X|X|x|"+"\033[0m"+"\n" +
               ""+"\033[31m"+"|x|X|X|x|"+"\033[0m"+"\n" +
               "+-------+";
    }
}
