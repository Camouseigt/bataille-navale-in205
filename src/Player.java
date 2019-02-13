import java.io.Serializable;
import java.util.List;

public class Player {
    /* **
     * Attributs
     */
    protected Board board;
    protected Board opponentBoard;
    protected int destroyedCount;
    protected AbstractShip[] ships;
    protected boolean lose;

    /* **
     * Constructeur
     */
    public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
        this.board = board;
        this.ships = ships.toArray(new AbstractShip[0]);
        this.opponentBoard = opponentBoard;
    }

    /* **
     * Méthodes
     */

    /**
     * Read keyboard input to get ships coordinates. Place ships on given coodrinates.
     */
    public void putShips() {
        boolean done = false;
        int i = 0;
        int info = -1;
        do {
            AbstractShip s = ships[i];
            String msg = String.format("placer %d : %s(%d)", i + 1, s.getName(), s.getLength());
            System.out.println(msg);
            InputHelper.ShipInput res = InputHelper.readShipInput();
            
            // TODO set ship orientation
            if (res.orientation == "e") {
            	s.orientation = 0;
            }
            if (res.orientation == "s") {
            	s.orientation = 1;
            }
            if (res.orientation == "w") {
            	s.orientation = 2;
            }
            if (res.orientation == "n") {
            	s.orientation = 3;
            }
            
            info = board.putShip(s, res.y, res.x);
            if (info == 0) {//Placement du bateau réussi
            	++i;
            	done = (i == 5);
            }
            
            sleep(2000);
            board.print();
        } while (!done);
    }

    public Hit sendHit(int[] coords) {
        boolean done = true;
        Hit hit = null;

        do {
            System.out.println("où frapper?");
            InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
            // TODO call sendHit on this.opponentBoard
            if (!(hitInput.x<=0 || hitInput.y<=0 || hitInput.x>10 || hitInput.y>10)) {
            	//Le tir est valide
            	hit = this.opponentBoard.sendHit(hitInput.x, hitInput.y);
            }
            

            // TODO : Game expects sendHit to return BOTH hit result & hit coords.
            hit.coords = hitInput;
            // return hit is obvious. But how to return coords at the same time ?
        } while (!done);

        return hit;
    }

    public AbstractShip[] getShips() {
        return ships;
    }

    public void setShips(AbstractShip[] ships) {
        this.ships = ships;
    }
    
    private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
