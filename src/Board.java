import java.util.ArrayList;


public class Board implements IBoard {
/* Attributs */
	
	public String name;
	public ArrayList<ArrayList<String>> grille;
	public ArrayList<ArrayList<Boolean>> tirs;
	
/* Constructeurs*/
	public Board(String nom_grille, int size_grille) {
		
		this.name = nom_grille;
		this.grille = new ArrayList<ArrayList<String>>();
		this.tirs = new ArrayList<ArrayList<Boolean>>();
		
		for (int i = 0; i<size_grille; i++) {
			
			ArrayList<String> ligne_grille = new ArrayList<String>();
			ArrayList<Boolean> ligne_tirs = new ArrayList<Boolean>();
			
			
			for (int j = 0; j<size_grille;j++) {
				ligne_grille.add(".");
				ligne_tirs.add(false);
			}
			
			grille.add(i, ligne_grille);
			tirs.add(ligne_tirs);
		}
	}
	
	public Board(String nom_grille) {
		this.name = nom_grille;
		this.grille = new ArrayList<ArrayList<String>>();
		this.tirs = new ArrayList<ArrayList<Boolean>>();
		
		for (int i = 0; i<10; i++) {
			
			ArrayList<String> ligne_grille = new ArrayList<String>();
			ArrayList<Boolean> ligne_tirs = new ArrayList<Boolean>();
			
			for (int j = 0; j<10;j++) {
				ligne_grille.add(".");
				ligne_tirs.add(false);
			}
			
			grille.add(ligne_grille);
			tirs.add(ligne_tirs);
			
		}
		
	}
	
	
/*Méthodes*/
	public void print() {
		String x = "Navires :";
		System.out.println(x);
		String ligne;
		for (int i = 0; i < grille.size(); i++) {
			ligne = "" + (i+1) + "\t";
			for (int j = 0; j < grille.size();j++) {	
				ligne += grille.get(i).get(j)+ " ";
			}
			System.out.println(ligne);	
		}
		
		x = "Frappes :";
		System.out.println("\n");
		System.out.println(x);
		for (int i = 0; i < grille.size(); i++) {
			ligne = "" + (i+1) + " \t";
			for (int j = 0; j < grille.size();j++) {
				if (tirs.get(i).get(j)) {
					ligne += "x ";
				}
				else {
					ligne += ". ";
				}
			}
			System.out.println(ligne);
			
		}
		
	}

	@Override
	public int getSize() {
		return grille.size();
	}

	@Override
	public int putShip(AbstractShip ship, int x, int y) {
		int size = ship.size;
		int orientation = ship.orientation;
		//On doit vérifier si on peut placer le bateau sur la grille
		boolean place = true;
		if (orientation%2 == 0) {//On est sur une ligne
			if (y+size > grille.size() || y-size < 0) {
				place = false;
			}
		}
		else {//On est sur une colonne
			if (x+size > grille.size() || x-size <0) {
				place = false;
			}
		}
		
		
		if (place) {
		ArrayList<String> ligne = new ArrayList<String>();
		
		if (orientation%2 == 0) {//On est sur une ligne
			//On récupère la ligne
			ligne = grille.get(y);
			for (int i = 0; i<size; i++) {
				if(orientation == 0) {
					ligne.set(x+i, ship.label);
				}
				else {
					ligne.set(x-i, ship.label);
				}
			}
		}
		
		
		
		else {//On est sur une colonne
			for (int i = 0; i<size; i++) {
					if(orientation == 1) {
						ligne = grille.get(x+i);
						ligne.set(y, ship.label);
						grille.set(x+i, ligne);
					}
					else {
						ligne = grille.get(x-i);
						ligne.set(y, ship.label);
						grille.set(x-i, ligne);
					}
			}
		}
		return 0;
		}
		else {//On ne peut pas placer le bateau
			System.out.println("On ne peut pas placer le bateau : "+ship.nom);
			return -1;
		}
		
	}

	@Override
	public boolean hasShip(int x, int y) {
		if (grille.get(x).get(y)==".") {
			return false;
		}
		return true;
	}

	@Override
	public void setHit(boolean hit, int x, int y) {
		ArrayList<Boolean> ligne = new ArrayList<Boolean>();
		ligne = tirs.get(x);
		ligne.set(y, true);
		tirs.set(x, ligne);
	}

	@Override
	public Boolean getHit(int x, int y) {
		if (grille.get(x).get(y)==".") {
			return false;
		}
		return true;
	}
}



