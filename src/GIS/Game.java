package GIS;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import Geom.Point3D;

/**
 * This class represent the data of the game, contains all the Fruit and Packman
 * @author Eti Reznikov and Chen Ostrovski
 *
 */
public class Game {

	public  ArrayList<Fruit> fruits;
	public  ArrayList<Packman> packmen;

	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	public ArrayList<Packman> getPackmen() {
		return packmen;
	}
	public Game() {
		fruits=new ArrayList<>();
		packmen=new ArrayList<>();
	}

	/**
	 * This function read the info from CSV file
	 * @param FileName the files name
	 */
	public void ReadCSV(String FileName){
		String csvFile = FileName;
		String line = "";
		String cvsSplitBy = ",";
		
		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{

			// Checks if the line is not empty
			while ((line = br.readLine()) != null) 
			{
	
				String[] data = line.split(cvsSplitBy);
				// If the data is Packmen data
				if (data[0].equals("P")) {
					Point3D point=new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]));
					Packman packman=new Packman(point,Long.parseLong(data[5]),Double.parseDouble(data[6]));
					packman.setLocationInPixle((int)packman.getLocationInPixle().x(),(int) packman.getLocationInPixle().y());
					packmen.add(packman);
				
				}
				// If the data is Fruits data
				else if (data[0].equals("F")) {
					Point3D point=new Point3D(Double.parseDouble(data[2]),Double.parseDouble(data[3]),Double.parseDouble(data[4]));
					Fruit fruit=new Fruit(point);
					fruit.setLocationInPixle((int)fruit.getLocationInPixle().x(), (int)fruit.getLocationInPixle().y());
					fruits.add(fruit);
				}
			}

		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}

	}
	/**
	 * This function write a CSV file
	 * @param output the name of the file
	 */
	public void WriteCSV(String output) {
		{
			String fileName = output+".csv";
			PrintWriter pw = null;

			try 
			{
				pw = new PrintWriter(new File(fileName));
			} 
			catch (FileNotFoundException e) 
			{
				e.printStackTrace();
				return;
			}
            // The headlines
			StringBuilder sb = new StringBuilder();
			sb.append("Type");
			sb.append(',');
			sb.append("Lat");
			sb.append(',');
			sb.append("Lon");
			sb.append(',');
			sb.append("Alt");
			sb.append(',');
			sb.append("Speed");
			sb.append(',');
			sb.append("Radius");
			sb.append('\n');
			pw.write(sb.toString());
			
			// Add Packman to the CSV
			Iterator<Packman> Packmaniter=packmen.iterator();
			while(Packmaniter.hasNext()) {
				Packman current=Packmaniter.next();
				sb.delete(0, sb.length());
				sb.append("P");
				sb.append(',');
				sb.append(current.toString());
				pw.append(sb);
			}
			// Add Fruit to the CSV
			Iterator<Fruit> Fruititer=fruits.iterator();
			while(Fruititer.hasNext()) {
				Fruit current=Fruititer.next();
				sb.delete(0, sb.length());
				sb.append("F");
				sb.append(',');
				sb.append(current.toString());
				pw.append(sb);
			}
			pw.close();
		}
	}
}
