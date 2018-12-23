package File_formats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import Algorithms.Solution;
import GIS.Path;

/**
 * This class converts a path into KML file
 * @author Ester Reznikov and Chen Ostrovski
 */
public class Path2KML {

	/**
	 * This function writes the KML file. It takes a path and convert it to KML.
	 * @param output The name of the file
	 * @param solution the ArrayList that contains all the paths
	 */
	public void writeFileKML( String output, Solution solution) {
		ArrayList<String> KML = new ArrayList<String>();
		//	String tofinalKML="";
		// The opening of the KML file
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n" +
				"<Document>" + "<Style id=\"s_ylw-pushpin_hl\">"+
				"<IconStyle>"+		"<color>ff0000ff</color>"+
				"<scale>1</scale>"+
				"<Icon>"+
				"<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>"+
				"</Icon>"+
				"</IconStyle>"+
				"</Style>";

		KML.add(kmlstart);
		// The ending of the KML file
		String kmlend = "\n</Document>"+"\n</kml>";
		String lines="";
		try{
			PrintWriter fw = new PrintWriter(new File(output+".kml"));
			BufferedWriter bw = new BufferedWriter(fw);
			for (Path path: solution){
				String packman = Double.toString(path.getPackman().getPackmanLocation().x())+","+Double.toString(path.getPackman().getPackmanLocation().y());
				String []startpointinfo=packman.split(",");
				lines=lines+ "<Placemark> \r\n" + 
						" <LineString>\r\n" + 
						"  <coordinates>\n" +
						path.getPackman().getPackmanLocation().y() + "," + path.getPackman().getPackmanLocation().x() +","+ 0 + "\n";
				String kmlelement ="<Placemark>\n" +
						"<Point>\n" +
						"<coordinates>"+startpointinfo[1]+","+startpointinfo[0]+"</coordinates>\n" +
						"</Point>\n" +
						"<description>"+", velocity: "+Long.toString(path.getPackman().getVelocity())+ " ,radius: "+Double.toString(path.getPackman().getRadius())+
						"</description>\n" +
						"<TimeStamp>\n" +
						"<when>"+CalendarUtils.ConvertMilliSecondsToFormattedDate(Long.toString(path.getCreation_Time()))+"</when>\n" +
						"</TimeStamp>\n" +
						"</Placemark>\n";
				KML.add(kmlelement);
				// Go over the ArrayList and add the info to the KML
				for (int i = 0; i < path.size(); i++) {
					String metadata = path.get(i).toString();
					String []matadatainfo=metadata.split(":");
					String[]point=matadatainfo[0].split( ",");
					lines=lines+ point[1] + "," + point[0] +","+ 0 + "\n";
					kmlelement ="<Placemark>\n" +
							"<styleUrl>#s_ylw-pushpin_hl</styleUrl>\n"+
							"<Point>\n" +
							"<coordinates>"+point[1]+","+point[0]+"</coordinates>\n" +
							"</Point>\n" +
							"<TimeStamp>\n" +
							"<when>"+CalendarUtils.ConvertMilliSecondsToFormattedDate(Long.toString(path.getCreation_Time()+Long.parseLong(matadatainfo[1])))+"</when>\n" +
							"</TimeStamp>\n" +
							"</Placemark>\n";
					KML.add(kmlelement);
				}
				lines=lines+"</coordinates>\r\n" + 
						" </LineString>\r\n" + 
						" <Style> \r\n" + 
						"  <LineStyle>  \r\n" + 
						"   <color>ffff0000</color>\r\n" + 
						"<width>6</width>"+
						"  </LineStyle> \r\n" + 
						" </Style>"+
						"</Placemark>\r\n";
				KML.add(lines);
			}

			KML.add(kmlend);
			// Change the ArrayList to String
			String KmlArrayToString="";
			for (int i=0; i<KML.size(); i++) {
				KmlArrayToString=KmlArrayToString+KML.get(i);		
			}
			// Add all the String to the KML
			bw.write(KmlArrayToString);
			bw.close();
		} 

		catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	/**
	 * This function add the time and date into the KML file
	 */
	public static class CalendarUtils {

		public static final String dateFormat = "yyyy-MM-dd hh:mm:ss";
		private final static SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);

		public static String ConvertMilliSecondsToFormattedDate(String milliSeconds){
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(Long.parseLong(milliSeconds));
			String date=simpleDateFormat.format(calendar.getTime());
			date=date.replace(" ", "T");
			return date;
		}
	}

}
