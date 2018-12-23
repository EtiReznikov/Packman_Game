package GIS;

import Geom.Point3D;

/**
 * This class contains data of points
 * @author Ester Reznikov and Chen Ostrovski
 */
public class Metadata implements Meta_data {

	private long time;
	private Point3D point;
	private String MAC, SSID, AuthMode;
	private String SeenAt;
	private Fruit fruit;

	public Metadata(Metadata metadata) {
		this.time=metadata.time;
		this.point=metadata.point;
		this.MAC=metadata.MAC;
		this.SSID=metadata.SSID;
		this.AuthMode=metadata.AuthMode;
		this.SeenAt=metadata.SeenAt;
		this.fruit=metadata.fruit;
	}
	public Metadata(Point3D point, long time) {
		this.point=point;
		this.time=time;
	}
	public Metadata(Point3D point) {
		this.point=point;
		this.time=-1;
	}
	public Metadata(long time, Fruit fruit) {
		this.time=time;
		this.fruit=fruit;
		this.point=fruit.getFruitLocation();
	}
	public Metadata() {
		this.time=System.currentTimeMillis();
	}
	public Metadata(Long creationTime) {
		this.time=creationTime;
	}
	@Override
	public long getUTC() {
		return this.time;
	}
	public String toString() {
		return point.toString()+":" + Long.toString(this.getTime());
	}
	@Override
	public Point3D get_Orientation() {
		return null;
	}

	/////////GET/////////

	public Fruit getFruit() {
		return fruit;
	}
	public long getTime() {
		return time;
	}
	public String getMAC() {
		return MAC;
	}
	public String getSSID() {
		return SSID;
	}
	public String getAuthMode() {
		return AuthMode;
	}
	public String getSeenAt() {
		return SeenAt;
	}
	public Point3D getPoint() {
		return point;
	}

	/////////SET/////////

	public void setPoint(Point3D point) {
		this.point = point;
		this.fruit.setFruitLocation(point);
	}
	public void setPoint(double x, double y, double z) {
		this.point = new Point3D(x,y,z);
	}
	public void setSeenAt(String seenAt) {
		SeenAt = seenAt;
	}
	public void setAuthMode(String authMode) {
		AuthMode = authMode;
	}
	public void setMAC(String mAC) {
		MAC = mAC;
	}
	public void setSSID(String sSID) {
		SSID = sSID;
	}
	public void setFruit(Fruit fruit) {
		this.fruit = fruit;
		this.point=fruit.getFruitLocation();
	}
	public void setTime(long time) {
		this.time = time;
	}




}
