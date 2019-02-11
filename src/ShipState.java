
public class ShipState {
	//Attributs
	public AbstractShip ship;
	public boolean struck;
	
	//Constructeur
	public ShipState() {//Représente une case sans bateau
		this.ship = new AbstractShip(".","NULL",1,0);
		this.struck = false;
	}
	
	public ShipState(AbstractShip ship,boolean struck) {
		this.ship = ship;
		this.struck = struck;
	}
	
	//Méthodes
	public void addStrike() {
		this.struck = true;
		this.ship.addStrike();/*Le navire est touché à cet endroit de la grille et on ajoute donc 1 au nombre de frappes subies
								On peut ne pas traiter le cas ou le le navire est touché plus de fois que sa longueur qui correspondrait à un joueur peu malin qui
								touche un bateau plusieur fois au même endroit ce qui est inutile*/
	}
	
	
	public boolean isStruck() {
		return this.struck;
	}
	
	public String toString(){
		if(this.isStruck()) {//Si le navire est touché en cet endroit, on affiche son label en rouge
			return ColorUtil.colorize(this.ship.label, ColorUtil.Color.RED);
		}
		else {
			return this.ship.label;
		}
	}
	
	public AbstractShip ship() {
		return this.ship;
	}
	
	public boolean isSunk() {
		return this.ship.isSunk();
	}
	
	public AbstractShip getShip() {
		return this.ship;
	}
}
