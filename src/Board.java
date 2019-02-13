public class Board implements IBoard {
/* Attributs */
	
	public String name;
	public ShipState[][] grille;
	public Boolean[][] tirs;
	
/* Constructeurs*/
	public Board(String nom_grille, int size_grille) {
		
		this.name = nom_grille;
		this.grille = new ShipState[size_grille][size_grille];
		this.tirs = new Boolean[size_grille][size_grille];
		
		for (int i = 0; i<size_grille; i++) {
			for (int j = 0; j<size_grille;j++) {
				ShipState empty = new ShipState();
				grille[i][j] = empty;
				tirs[i][j] = null;
			}
		}
	}
	
	public Board(String nom_grille) {
		int default_size = 10;
		this.name = nom_grille;
		this.grille = new ShipState[default_size][default_size];
		this.tirs = new Boolean[default_size][default_size];
		
		for (int i = 0; i<default_size; i++) {
			for (int j = 0; j<default_size;j++) {
				ShipState empty = new ShipState();
				grille[i][j] = empty;
				tirs[i][j] = null;
			}
		}
	}
	
	
/*Méthodes*/
	public void print() {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String x = "Navires : \t\t\tFrappes :";
		System.out.println(x);
		x = "";
		for(int i=0; i < this.getSize(); i++) {
			x += alphabet.charAt(i) + " ";
			
		}
		x = "\t" + x + "\t\t" + x;
		System.out.println(x);
		String ligne;
		
		for (int i = 0; i < this.getSize(); i++) {
			ligne = "" + (i+1) + "\t";
			for (int j = 0; j < this.getSize();j++) {	
				String label = new String();
				label = grille[i][j].toString();
				ligne += label + " ";
			}
			ligne += "\t" + (i+1) + " \t";
			for (int j = 0; j < this.getSize();j++) {
				if (tirs[i][j]==null) {
					ligne += ". ";
				}
				else {
					if(tirs[i][j]) {//Un bateau a été touché
						ligne += ColorUtil.colorize("X ", ColorUtil.Color.RED);
					}
					else {
						ligne += ColorUtil.colorize("X ", ColorUtil.Color.WHITE);
					}
				}
			}
			System.out.println(ligne);	
		}
	}

	@Override
	public int getSize() {
		return this.grille.length;
	}

	@Override
	public int putShip(AbstractShip ship, int x, int y) {
		int size = ship.size;
		int orientation = ship.orientation;
		ShipState s;
		int dx = 0, dy = 0;
		//On doit vérifier si on peut placer le bateau sur la grille
		boolean place = true;
		if (x<0 || y<0 || x>=this.getSize() || y>=this.getSize()) {
			place = false;
		}
		if (orientation == 0) dy = 1;
		if (orientation == 2) dy = -1;
		if (orientation == 1) dx = 1;
		if (orientation == 3) dx = -1;
		
		if (orientation == 0 && y+size > this.getSize()) {//East
			place = false;
		}
		else if (orientation == 2 && y-size+1 < 0) {//West
			place = false;
		}
		else if (orientation == 1 && x+size > this.getSize()) {//South
			place = false;
		}
		else if (orientation == 3 && x-size+1 < 0) {//North
			place = false;
		}
		
		int ix = x;
        int iy = y;
        if (place) {
        	for (int i = 0; i < ship.getLength(); ++i) {
        		if (this.hasShip(ix, iy)) {
        			place = false;
        		}
        		ix += dx;
        		iy += dy;
        	}
        }
		
		
		if (place) {
			if (orientation%2 == 0) {//On est sur une ligne
				for (int i = 0; i<size; i++) {
					if(orientation == 0) {
						s = new ShipState(ship, false);
						grille[x][y+i] = s;
					}
					else {
						s = new ShipState(ship, false);
						grille[x][y-i] = s;
					}
				}
			}

		else {//On est sur une colonne
			for (int i = 0; i<size; i++) {
					if(orientation == 1) {
						s = new ShipState(ship, false);
						grille[x+i][y] = s;
					}
					else {
						s = new ShipState(ship, false);
						grille[x-i][y] = s;
					}
			}
		}
		return 0;//Placement réussi
		}
		else {//On ne peut pas placer le bateau
			System.out.println("On ne peut pas placer le bateau : "+ship.nom);
			return -1;
		}
		
	}

	@Override
	public boolean hasShip(int x, int y) {
		if (grille[x][y].ship().label==".") {
			return false;
		}
		return true;
	}

	@Override
	public void setHit(boolean hit, int x, int y) {
		tirs[x][y] = hit;
	}

	@Override
	public Boolean getHit(int x, int y) {
		if (tirs[x][y]==null) {
			return false;
		}
		return true;
	}
	
	public Hit sendHit(int x, int y) {
		Hit res;
		ShipState s = new ShipState();
		s = grille[x][y];
		if(s.ship().label != ".") {//Un bateau a été touché
			res = Hit.STRIKE;
			s.addStrike();
			System.out.println("Hit !");
			if(s.isSunk()) {//Le bateau a été coulé
				String name = s.ship().getName();
				if(name == "Carrier") {
					res = Hit.CARRIER;
					System.out.println("Carrier Coulé!");
				}
				if(name == "Battleship") {
					res = Hit.BATTLESHIP;
					System.out.println("Battleship Coulé!");
				}
				if(name == "Destroyer") {
					res = Hit.DESTROYER;
					System.out.println("Destroyer Coulé!");
				}
				if(name == "Submarine") {
					res = Hit.SUBMARINE;
					System.out.println("Submarine Coulé!");
				}
			}
			return res;
		}
		else {
			res = Hit.MISS;
			System.out.println("Coup dans l'eau !");
			return res;
		}
	}
	
	
}



