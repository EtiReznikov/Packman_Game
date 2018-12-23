package File_formats;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import GIS.GisElement;
import GIS.Layer;
import GIS.Metadata;

/**
 * This class convert CSV file into KML file
 * Gets path to file and convert it
 * @author Eti Reznikov and Chen Ostrovski
 */
public class Csv2Kml {
	Layer Layer_GIS;

	public Csv2Kml() {
		Layer_GIS=new Layer();
	}
	public Layer getLayer_GIS() {
		return Layer_GIS;
	}
	public void setLayer_GIS(Layer layer_GIS) {
		Layer_GIS = layer_GIS;
	}
	/**
	 * This method read the information from a CSV file and saves each row in GisElement
	 * @param inputName the path of the CSV file
	 * @param outputName the name that the user want to give to the KML file
	 */
	public String ReadCSV(String inputName, String outputName) {
		ArrayList<String []> toKML=new ArrayList<>();
		String tofinalKML;
		// Creates a new name to the kml file
		String nameFile=outputName+".kml";
		// The path of the csv file
		String csvFile = inputName;
		String line = "";
		String cvsSplitBy = ",";
		int thisLine=-1;

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{

			// Checks if the line is not empty
			while ((line = br.readLine()) != null) 
			{
				thisLine++;
				String[] data = line.split(cvsSplitBy);
				// Start to read info from line two
				if (thisLine>1) {
					// Add the info of the line and add it to the GisElement and to the KML info
					toKML.add(data);	
					Metadata metadata=new Metadata();
					
					metadata.setMAC(data[0]);
					metadata.setSSID(data[1]);
					metadata.setAuthMode(data[2]);
					metadata.setSeenAt(data[3]);
					metadata.setPoint(Double.parseDouble(data[6]),Double.parseDouble(data[7]),Double.parseDouble(data[8]));
					
					GisElement gis=new GisElement(metadata);
					this.getLayer_GIS().add(gis);
				}
			}
		}

		catch (IOException e) 
		{

			e.printStackTrace();

		}
		// CAll the writeFileKML function and return String that contains all the info of the elements
		tofinalKML=this.writeFileKML(toKML,nameFile);
		return tofinalKML;
	}


	/**
	 * Write the KML file of one layer
	 * @param toKML ArrayList that contains all the info of the layer
	 * @param output the name of the file
	 * @return toFinalKML string that contains all the info of the KML
	 */
	public String writeFileKML(ArrayList<String[]> toKML, String output) {
		ArrayList<String> KML = new ArrayList<String>();
		String tofinalKML="";
		// The opening of the KML file
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
				+ " <Document> <Folder><name>Wifi Networks</name>\n";

		KML.add(kmlstart);
		// The ending of the KML file
		String kmlend = "</Folder>\n</Document>"+"\n</kml>";
		try{
			FileWriter fw = new FileWriter(output);
			BufferedWriter bw = new BufferedWriter(fw);
			// Go over the ArrayList and add the info to the KML
			for (int i = 0; i < toKML.size(); i++) {
				String[] s = toKML.get(i);
				String kmlelement ="<Placemark>\n" +
						"<description>"+"name"+s[1]+"name" +
						s[0]+", Capabilities:"+s[2]+", Date:"+s[3]+
						"</description>\n" +
						"<Point>\n" +
						"<coordinates>"+s[7]+","+s[6]+"</coordinates>\n" +
						"</Point>\n" +
						"<TimeStamp>\n" +
						"<when>"+s[3].replace(" ", "T")+"</when>\n" +
						"</TimeStamp>\n" +
						"</Placemark>\n";
				KML.add(kmlelement);
				tofinalKML=tofinalKML+kmlelement;
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
		return tofinalKML;
	}

}

