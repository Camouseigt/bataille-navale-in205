
public class TestGame {
	
	public static void main(String[] args){
		Board test = new Board("test",10);
		AbstractShip ship1 = new Destroyer(0);
		AbstractShip ship2 = new Carrier(1);
		
		test.putShip(ship1, 5, 5);
		test.putShip(ship2, 5, 4);
		test.print();
		
		
		BattleShipsAI ai = new BattleShipsAI(test,test);
		
		
		int countDestroyedShip = 0;
		int[] coords = new int[2];
		while(countDestroyedShip !=2){
			coords = ai.pickRandomCoord();
			ai.sendHit(coords);
			ai.board.print();
			sleep(200);
		}
		System.out.println("Test 1");
	}
	
	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
