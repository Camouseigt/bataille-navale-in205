import java.util.ArrayList;


public class Board {
/* Attributs */
	
	public String name;
	public ArrayList<ArrayList<String>> grille;
	public ArrayList<ArrayList<Boolean>> tirs;
	
/* Constructeurs*/
	public Board(String nom_grille, int size_grille) {
		
		this.name = nom_grille;
		this.grille = new ArrayList<ArrayList<String>>();
		this.tirs = new ArrayList<ArrayList<Boolean>>();
		ArrayList<String> ligne_grille = new ArrayList<String>();
		ArrayList<Boolean> ligne_tirs = new ArrayList<Boolean>();

		for (int i = 0; i<size_grille; i++) {
			
			ligne_grille.clear();
			ligne_tirs.clear();
			
			
			for (int j = 0; j<size_grille;j++) {
				ligne_grille.add(".");
				ligne_tirs.add(false);
			}
			
			
			grille.add(ligne_grille);
			tirs.add(ligne_tirs);
		}
	}
	
	public Board(String nom_grille) {
		this.name = nom_grille;
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
			ligne = "" + i + " ";
			for (int j = 0; j < grille.size();j++) {	
				ligne += grille.get(i).get(j)+ " ";
			}
			System.out.println(ligne);	
		}
		
		x = "Frappes :";
		System.out.println("\n");
		System.out.println(x);
		for (int i = 0; i < grille.size(); i++) {
			ligne = "" + i + " ";
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

}



