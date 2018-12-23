package GIS;

import Algorithms.Map;
import Geom.Point3D;


/**
 * This class represent the Packman and all of its data
 * @author Eti Reznikov and Chen Ostrovski
 */
public class Packman {
	private Point3D packmanLocation;
	private Point3D LocationInPixle;
	private long velocity;
	private double radius;
	private double Orientation;
	private Metadata closestFruit;
	private static int PackmenCounter=-1;
	private int id;
	private int garde=0;
	public Path path;
	static Map map=new Map();
	
	public Packman (Packman packman) {
		this.packmanLocation = packman.packmanLocation;
		this.setLocationInPixle(Map.getPositionOnScreen(packmanLocation));
		this.velocity=packman.velocity;
		this.radius=packman.radius;
		this.path=packman.path;
		this.closestFruit=packman.closestFruit;
		this.id=++PackmenCounter;
	}

	public Packman(Point3D packmanLocation) {
		this.packmanLocation = packmanLocation;
		this.setLocationInPixle(Map.getPositionOnScreen(packmanLocation));
		this.velocity=1;
		this.radius=1;
		this.path=new Path(this);
		this.closestFruit=new Metadata(packmanLocation,0);
		this.id=++PackmenCounter;
	}
	public Packman(Point3D packman, long velocity) {
		this.packmanLocation = packman;
		this.velocity=velocity;
		this.path=new Path(this);
		this.closestFruit=new Metadata(packmanLocation,0);
		this.LocationInPixle= Map.getPositionOnScreen(this.packmanLocation);
		this.id=++PackmenCounter;
	}
	public Packman(Point3D packman, long velocity, double radius) {
		this.packmanLocation = packman;
		this.velocity=velocity;
		this.radius=radius;
		this.path=new Path(this);
		this.closestFruit=new Metadata(packmanLocation,0);
		this.LocationInPixle= Map.getPositionOnScreen(this.packmanLocation);
		this.id=++PackmenCounter;
	}
	public String toString() {
		return this.packmanLocation.x()+","+this.packmanLocation.y()+
				","+this.packmanLocation.z()+","+this.velocity+","+this.radius+"\n" ;
	}

	/////////GET/////////

	public Point3D getPackmanLocation() {
		return packmanLocation;
	}
	public int getGarde() {
		return garde;
	}
	public int getId() {
		return id;
	}
	public long getVelocity() {
		return velocity;
	}
	public double getRadius() {
		return radius;
	}
	public Path getPath() {
		return this.path;
	}
	public Point3D getLocationInPixle() {
		return this.LocationInPixle;
	}
	public Metadata getClosestFruit() {
		return closestFruit;
	}

	/////////SET/////////
	
	public void setVelocity(long velocity) {
		this.velocity = velocity;
	}
	public void setGarde(int garde) {
		this.garde = garde;
	}
	public void setPackmanLocation(Point3D packmanLocation) {
		this.packmanLocation=packmanLocation;
		this.setLocationInPixle(Map.getPositionOnScreen(packmanLocation));
	}
	public void setPackmanLocation(double x, double y, double z) {
		this.packmanLocation.set_x(x);
		this.packmanLocation.set_y(y);
		this.packmanLocation.set_z(z);
		this.setLocationInPixle(Map.getPositionOnScreen(packmanLocation));
	}
	public void setPackmanLocation(double x, double y) {
		this.packmanLocation.set_x(x);
		this.packmanLocation.set_y(y);
		this.setLocationInPixle(Map.getPositionOnScreen(packmanLocation));
	}
	public void setClosestFruit(Metadata closestFruit) {
		this.closestFruit = closestFruit;
	}
	public void setClosestFruit(Point3D point, long time) {
		closestFruit.setPoint(point);
		closestFruit.setTime(time);
	}
	public void setClosestFruit(Fruit fruit, long time) {
		closestFruit.setFruit(fruit);
		closestFruit.setTime(time);
	}
	public void setLocationInPixle(Point3D locationInPixle) {
		LocationInPixle = locationInPixle;
	//	packmanLocation=Map.PixeltoCoordanite(locationInPixle);
	}
	public void setLocationInPixle(int x, int y) {
		this.LocationInPixle.set_x(x);
		this.LocationInPixle.set_y(y);
		packmanLocation=Map.PixeltoCoordanite(this.LocationInPixle);	
	}

}
