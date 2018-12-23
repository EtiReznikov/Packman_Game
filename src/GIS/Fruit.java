package GIS;

import Algorithms.Map;
import Geom.Point3D;

/**
 * This class represent Fruits and all the data hat they contain
 * @author Eti Reznikov and Chen Ostrovski
 *
 */
public class Fruit {
	private Point3D fruitLocation;
	private Point3D LocationInPixle;
	private static int FruitCounter=-1;
	private int id;

	public Fruit(Point3D fruit) {
		this.fruitLocation = fruit;
		this.LocationInPixle=Map.getPositionOnScreen(fruitLocation);
		this.id=++FruitCounter;
	}
	public Fruit(Fruit fruit) {
		this.fruitLocation=fruit.fruitLocation;
		this.LocationInPixle=Map.getPositionOnScreen(fruitLocation);
		this.id=++FruitCounter;
	}
	public int getId() {
		return id;
	}
	
	public Point3D getLocationInPixle() {
		return LocationInPixle;
		
	}
	public void setLocationInPixle(Point3D locationInPixle) {
		this.LocationInPixle = locationInPixle;
		fruitLocation=Map.PixeltoCoordanite(locationInPixle);
		
	}
	public void setLocationInPixle(int x, int y) {
		this.LocationInPixle.set_x(x);
		this.LocationInPixle.set_y(y);
		fruitLocation=Map.PixeltoCoordanite(this.LocationInPixle);	
	}
	public Point3D getFruitLocation() {
		return fruitLocation;
	}
	public String toString() {
		return this.fruitLocation.x()+","+this.fruitLocation.y()+"\n";
	}
	public void setFruitLocation(Point3D fruitLocation) {
		this.fruitLocation = fruitLocation;
	}
	public void setFruitLocation(double x, double y) {
		this.fruitLocation.set_x(x);
		this.fruitLocation.set_y(y);
	}



}
