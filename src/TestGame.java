
public class TestGame {
	
	public static void main(String[] args){
		Board test = new Board("test",10);
		AbstractShip ship1 = new Destroyer(0);
		AbstractShip ship2 = new Carrier(1);
		AbstractShip[] ships = new AbstractShip[2];
		ships[0] = ship1;
		ships[1] = ship2;
		BattleShipsAI ai = new BattleShipsAI(test,test);
		//ai.putShips(ships);
		ai.board.putShip(ship1, 0, 0);
		ai.board.putShip(ship2, 2, 0);
		
		//ai.board.print();
		//ai.opponent.print();
		
		Hit res;
		int countDestroyedShip = 0;
		int[] coords = new int[2];
		res = ai.board.sendHit(0, 0);
		if(res == Hit.MISS) {
			ai.board.setHit(false,coords[0],coords[1]);
		}
		else {
			ai.board.setHit(true,coords[0],coords[1]);
		}
		ai.board.print();
		sleep(100);
		
		
		while(countDestroyedShip !=2){
			coords = ai.pickRandomCoord();
			res = ai.sendHit(coords);
			if(res == Hit.MISS) {
				ai.board.setHit(false,coords[0],coords[1]);
				ai.board.print();
			}
			else {
				if(res != Hit.STRIKE) {
					countDestroyedShip++;
				}
				ai.board.setHit(true,coords[0],coords[1]);
				ai.board.print();
			}
			sleep(100);
		}
		System.out.println("Partie terminée");
	}
	
	private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
