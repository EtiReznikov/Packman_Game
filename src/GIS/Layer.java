package GIS;


import java.util.HashSet;
/**
 * This class creates a new layer that contains all the information from the CSV file
 * @author Eti Reznikov and Chen Ostrovski
 */
public class Layer extends HashSet<GisElement>implements GIS_layer{

	private Long creation_Time;
	private static final long serialVersionUID = 1L;
	/**
	 * This function creates Metadata with the time that the project was created
	 * @return metadata 
	 */
	@Override
	public Metadata get_Meta_data() {
		Metadata metadata=new Metadata(creation_Time);
		return metadata;
	}

	public Layer() {
		this.creation_Time=System.currentTimeMillis();
	}







}
