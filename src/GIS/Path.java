package GIS;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;
import Geom.Point3D;

/**
 * This class represent the path of Packman
 * @author Eti Reznikov and Chen Ostrovski
 *
 */
public class Path extends ArrayList<Metadata>{

	
	private static final long serialVersionUID = 1L;
	private long time;
	private Packman packman;
	private Date creation_Date;
	private long creation_Time;
	private Point3D startPoint;
	private Color color;

	public Path(Packman packman) {
		creation_Time=System.currentTimeMillis();
		creation_Date=new Date(creation_Time);
		time=0;
		this.packman=packman;
		this.startPoint=packman.getPackmanLocation();
		color=new Color((int)(Math.random() * 0x1000000));
	}

	public Path(Path p) {
		this.time=p.time;
	}

	////////GET////////
	
	public Point3D getStartPoint() {
		return startPoint;
	}
	public Date getCreation_Date() {
		return creation_Date;
	}
	public long getCreation_Time() {
		return creation_Time;
	}
	public Packman getPackman() {
		return packman;
	}
	public Path(long time) {
		this.time=time;
	}
	public long getTime() {
		return time;
	}
	
	public Color getColor() {
		return color;
	}

	////////SET////////
	
	public void setTime(long time) {
		this.time = time;
	}
}
