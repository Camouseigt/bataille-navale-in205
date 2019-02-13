
public class TestBoard extends Board {

	public TestBoard(String nom_grille, int size_grille) {
		super(nom_grille, size_grille);
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		Board test = new Board("test",10);
		//test.print();
		AbstractShip ship = new Destroyer(2);
		test.putShip(ship,2,2);
		ship = new Carrier(3);
		test.putShip(ship,1,5);
		/*test.sendHit(5,5);
		test.sendHit(4,5);
		test.sendHit(3,5);
		test.sendHit(2,5);
		test.sendHit(1,5);	*/	
		test.print();
	}

}
