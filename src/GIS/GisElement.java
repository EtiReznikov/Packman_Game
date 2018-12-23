package GIS;

import Coords.MyCoords;
import Geom.Geom_element;
import Geom.Point3D;

/**
 * This class represents every line in the CSV file
 * @author Eti Reznikov and Chen Ostrovski
 */
public class GisElement implements GIS_element {
	private Metadata metadata;

	public GisElement( Metadata metadata) {
		this.setMetadata(metadata);
	}
	public GisElement( GisElement gis) {
		this.setMetadata(gis.getMetadata());
	}

	/**
	 * This function return the point coordinate
	 */
	@Override
	public Geom_element getGeom() {
		return this.metadata.getPoint();
	}
	/**
	 * This function return the metadata
	 */
	@Override
	public Meta_data getData() {
		return this.metadata;
	}
	/**
	 * This function take vector and changes the point coordinate in accordance
	 * @param vec the vector that changes the point
	 */
	@Override
	public void translate(Point3D vec) {
		MyCoords coord=new MyCoords();
		this.metadata.setPoint((coord.add(this.metadata.getPoint(), vec)));
	}

	public void setPoint(double x, double y, double z) {
		metadata.setPoint(x,y,z);
	}

	public void setMetadata(Metadata metadata) {
		this.metadata = metadata;
	}

	public Metadata getMetadata() {
		return metadata;
	}
	public Point3D getPoint() {
		return this.getMetadata().getPoint();
	}

}
