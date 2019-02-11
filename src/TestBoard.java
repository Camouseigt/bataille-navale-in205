
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
		test.sendHit(5,5);
		//System.out.println(test.grille.get(5).get(4).struck);
		//test.sendHit(5, 6);
		//test.sendHit(5, 7);
		//System.out.println(test.hasShip(5, 5));
		//System.out.println(test.grille.get(5).get(5).ship.strikeCount);
		test.print();
	}

}
