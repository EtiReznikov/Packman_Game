import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Coords.MyCoords;
import Geom.Point3D;

class TestMyCoords {

	@Test
	public void test_add() throws Exception{
		MyCoords test=new MyCoords();
		Point3D p0 =new Point3D (32.10332, 35.20904,670);
		Point3D Vector =new Point3D (337.699, -359.249,-20);
		Point3D p1 = new Point3D(test.add(p0,Vector));
		Point3D expected = new Point3D(32.10635700007139,35.20630321469891,650.0);
		if(!p1.equals(expected)) {
			fail("Error");
		}
	}

	@Test
	public void test_distance3d() throws Exception{
		MyCoords test=new MyCoords();
		Point3D p0 =new Point3D (32.10332, 35.20904,670);
		Point3D p1=new Point3D (32.10635, 35.20523,650);
		double expected = 492.2447784618046;
		if(test.distance3d(p0, p1)!=expected) {
			fail("Error");
		}
	}

	@Test
	public void test_vector3D() throws Exception{
		new MyCoords();
		new Point3D (32.10332, 35.20904,670);
		new Point3D (32.10635, 35.20523,650);
		Point3D Vector =new Point3D (336.9206275762522,-358.87241832792813,-20.0);
		Point3D expected = new Point3D(336.9206275762522,-358.87241832792813,-20.0);
		if(!Vector.equals(expected)) {
			fail("Error");
		}
		
		
	}
	@Test
	public void test_azimuth_elevation_dist() throws Exception{
		MyCoords test=new MyCoords();
		Point3D p0 =new Point3D (32.10332, 35.20904,670);
		Point3D p1=new Point3D (32.10635, 35.20523,650);
		double []arr = new double[3];
		arr=test.azimuth_elevation_dist(p0,p1);
		double []expected = {313.1944438008807, -2.327938538713343, 492.2447784618046};
		for(int i=0; i<arr.length;i++) {
			if(arr[i]!=expected[i]) {
				fail("Error");
			}
		}
		
	}
	@Test
	public void test_isValid_GPS_Point() throws Exception{
		MyCoords test=new MyCoords();
		Point3D p0 =new Point3D (32.10332, 35.20904,670);
		if(test.isValid_GPS_Point(p0)!=true) {
			fail("Error");
		}
	}
	

}
