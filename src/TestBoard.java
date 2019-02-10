
public class TestBoard extends Board {

	public TestBoard(String nom_grille, int size_grille) {
		super(nom_grille, size_grille);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Board test = new Board("test",10);
		//test.print();
		AbstractShip ship = new Destroyer(0);
		test.putShip(ship,5,5);
		test.setHit(false, 5, 5);
		test.print();
	}

}
