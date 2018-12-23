import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Algorithms.Map;
import Geom.Point3D;

class TestMap {

	@Test
	public void Map_CoordsToPixle() {
		Map map=new Map();
		Point3D point=new Point3D(32.10598077,35.20286214,0);
		Point3D ans=new Point3D(42.0,9.0,0.0);
		if (!ans.equals(Map.getPositionOnScreen(point)))
			fail("Error");
	}
	@Test
	public void Map_PixleToCoords() {
		Map map=new Map();
		Point3D point=new Point3D(42.0,9.0,0.0);
		Point3D ans=new Point3D(32.11,35.2,0);
		point=Map.PixeltoCoordanite(point);
		double pointX=Math.round(point.x()*100.0)/100.0;
		double pointY=Math.round(point.y()*100.0)/100.0;
		double pointZ=Math.round(point.z()*100.0)/100.0;
		point.set_x(pointX);
		point.set_y(pointY);
		point.set_z(pointZ);
		if (!ans.equals(point))
			fail("Error");
	}

}
