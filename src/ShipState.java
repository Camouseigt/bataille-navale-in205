
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
		if(!struck) {
			this.struck = true;
			this.ship.addStrike();
		}
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
