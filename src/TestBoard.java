
public class TestBoard extends Board {

	public TestBoard(String nom_grille, int size_grille) {
		super(nom_grille, size_grille);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Board test = new Board("test",10); 
		test.print();
	}

}
