package Algorithms;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import File_formats.Csv2Kml;
import GIS.Project;

/**
 * This class create KML file to every layer and one KML file to the project
 * Gets path to folder and search recursively inside for CSV files
 * @author Eti Reznikov and Chen Ostrovski
 */
public class MultiCSV {

	Project GISproject;
	// Collect all the data of the KML files
	String toKML;

	public MultiCSV() {
		GISproject=new Project();
		toKML="";
	}
	/**
	 * This method creates one KML file to the project
	 * Use the multiCSV function to go recursively on the files
	 * Use the finalKML function to create one KML file
	 * @param path the folder/file path in the computer
	 */
	public static void fileManipulation(String path) {
		// Take all the info that found in the multiCSV function and put it in one CSVfolder
		MultiCSV CSVfolder=new MultiCSV();
		multiCSV(path, CSVfolder) ;
		// Create one KML file
		finalKML(CSVfolder.toKML);
		
	}
	
	/**
	 * Auxiliary function help to convert a CSV files into KML files
	 * Creates KML file to every layer
	 * Gets path to folder and search recursively inside for CSV files
	 * @param path the folder/file path in the computer
	 */
	public static void multiCSV(String path, MultiCSV folder){
		// Path to folder
		File myDirectory = new File(path);
		// Contains all the files within the folder
		File[] allFiles= myDirectory.listFiles();
		for (int i=0; i<allFiles.length; i++){
			File current= allFiles[i];
			// Checks if the files exists
			if (current.exists()) {
				// Checks if the file is folder
				if (current.isDirectory()) {
					// If the file is folder call the method recursively
					multiCSV(current.getAbsolutePath(), folder);
				}
				else
					// Checks the length of the file name
					if (allFiles[i].length()>=5) {
						String FileName=allFiles[i].getName();
						// Checks if the file is from the form CSV
						if (FileName.substring(FileName.length()-4,FileName.length()).equals(".csv")) {
							// Creates a new KML file
							Csv2Kml csv2kml=new Csv2Kml();
							folder.toKML=folder.toKML+csv2kml.ReadCSV(allFiles[i].getPath(),FileName.substring(0,FileName.length()-4));
							// Add the layer to the project
							folder.getGISproject().add(csv2kml.getLayer_GIS());
						}
					}
			}
		}
	}
	
	/**
	 * Auxiliary function help to create one KML file to the project
	 * @param toKML the info of the KML file 
	 */
	public static void finalKML(String toKML) {
		// The final KML data
		String kmlFinal="";
		
		String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n"
				+ " <Document> <Folder><name>Wifi Networks</name>\n";
		kmlFinal=kmlFinal+kmlstart;
		String kmlend = "</Folder>\n</Document>"+"\n</kml>";
		try{
			// Create file of KML
			FileWriter fw = new FileWriter("GIS_Project.kml");
			BufferedWriter bw = new BufferedWriter(fw);			 
			kmlFinal=kmlFinal+toKML;

			kmlFinal=kmlFinal+kmlend;
			// Write the KML file and close it
	
			
			bw.write(kmlFinal);
			bw.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Project getGISproject() {
		return GISproject;
	}

	public void setGISproject(Project gISproject) {
		GISproject = gISproject;
	}

}
