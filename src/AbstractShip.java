
public class AbstractShip {
/*Attributs*/
	public String label;
	public String nom;
	public int size;
	public int orientation;
	
/*Constructeur*/
	public AbstractShip(String label, String nom, int size, int orientation) {
		this.label = label;
		this.nom = nom;
		this.size = size;
		this.orientation = orientation;
	}
	
	public String getName() {
		return this.nom;
	}
	
	public int getLength() {
		return this.size;
	}
	
}
