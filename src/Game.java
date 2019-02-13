import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Game {

    /* ***
     * Constante
     */
	int size_grille = 10;
    public static final File SAVE_FILE = new File("savegame.dat");


	
    /* ***
     * Attributs
     */
    private Player player1;
    private AIPlayer player2;
    private Scanner sin;

    /* ***
     * Constructeurs
     */
    public Game() {}

    public Game init() {
        if (true /*!loadSave()*/) {
            // init attributes
        	List<AbstractShip> player1Ships = createDefaultShips();
            List<AbstractShip> player2Ships = createDefaultShips();
            System.out.println("entre ton nom:");
            
            // TODO use a scanner to read player name
            Scanner scanner = new Scanner(System.in);
            String username = scanner.nextLine();
            System.out.println("Votre nom est : " + username);

            // TODO init boards
            Board b1 = new Board(username,size_grille);
            Board b2 = new Board("AI board",size_grille);
            
            
            // TODO init this.player1 & this.player2
            player1 = new Player(b1,b2,player1Ships);
            player2 = new AIPlayer(b2,b1,player2Ships);
            b1.print();
          
            
            // place player ships
            player1.putShips();
            sleep(5000);
            player2.putShips();
        }
        return this;
    }

    /* ***
     * Méthodes
     */
    public void run() {
    	
        int[] coords = new int[2];
        Board b1 = player1.board;
        Hit hit;
        boolean done;
        // main loop
        
        
        do {
        	System.out.println(b1.name);
        	b1.print();
        	
        	// TODO player1 send a hit
            hit = player1.sendHit(coords);
            
            
            // TODO set this hit on his board (b1)
            //On modifie la grille tirs du joueur pour enregistrer son tir
            if(hit == Hit.MISS) {
            	b1.setHit(false,hit.coords.x, hit.coords.y);
            }
            if(hit == null) {
            	//On passe
            }
            else {
            	b1.setHit(true,hit.coords.x, hit.coords.y);

            }
            boolean strike = hit != Hit.MISS; 
            done = updateScore();
            b1.print();
            System.out.println(makeHitMessage(false /* outgoing hit */, coords, hit));

            
            //save();

            if (!done && !strike) {
                do {
                	// TODO player2 send a hit.
                	hit = player2.sendHit(player2.ai.pickRandomCoord()); 

                    strike = hit != Hit.MISS;
                    if (strike) {
                        b1.print();
                    }
                    System.out.println(makeHitMessage(true /* incoming hit */, coords, hit));
                    done = updateScore();

                    if (!done) {
                        //save();
                    }
                } while(strike && !done);
            }

        } while (!done);

        SAVE_FILE.delete();
        System.out.println(String.format("joueur %d gagne", player1.lose ? 2 : 1));
        sin.close();
    }


    private void save() {
    	try {
            FileOutputStream out = new FileOutputStream(SAVE_FILE);
            ObjectOutputStream oout = new ObjectOutputStream(out);
            oout.writeObject(player1);
            oout.writeObject(player2);
            oout.flush();
            oout.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private boolean loadSave() {
        if (SAVE_FILE.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE));

                player1 = (Player) ois.readObject();
                player2 = (Player) ois.readObject();

                ois.close();

                return true;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private boolean updateScore() {
        for (Player player : new Player[]{player1, player2}) {
            int destroyed = 0;
            for (AbstractShip ship : player.getShips()) {
                if (ship.isSunk()) {
                    destroyed++;
                }
            }

            player.destroyedCount = destroyed;
            player.lose = destroyed == player.getShips().length;
            if (player.lose) {
                return true;
            }
        }
        return false;
    }

    private String makeHitMessage(boolean incoming, int[] coords, Hit hit) {
        String msg;
        ColorUtil.Color color = ColorUtil.Color.RESET;
        switch (hit) {
            case MISS:
                msg = hit.toString();
                break;
            case STRIKE:
                msg = hit.toString();
                color = ColorUtil.Color.RED;
                break;
            default:
                msg = hit.toString() + " coulé";
                color = ColorUtil.Color.RED;
        }
        msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>",
                ((char) ('A' + coords[0])),
                (coords[1] + 1), msg);
        return ColorUtil.colorize(msg, color);
    }

    private static List<AbstractShip> createDefaultShips() {
        return Arrays.asList(new AbstractShip[]{new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()});
    }

    public static void main(String args[]) {
        new Game().init().run();
    }
    
    private static void sleep(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
				e.printStackTrace();
		}
	}
}
