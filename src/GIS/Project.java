package GIS;

import java.util.HashSet;
import java.util.Iterator;
/**
 * This class creates a new project that contains all the information from the CSV files
 * @author Eti Reznikov and Chen Ostrovski
 */
public class Project extends HashSet<GisElement> implements GIS_layer{

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
	
	/**
	 * This function add all the elements in the layer
	 * @param layer 
	 * @return true if all the elements was added false otherwise
	 */
	public boolean add(Layer layer) {
		boolean flag;
		boolean finalFlag=true;;
		Iterator<GisElement> iter=layer.iterator(); 
		while (iter.hasNext()) {
			flag=this.add(iter.next());
			if (!flag)
				finalFlag=false;
		}	
		return finalFlag;
	}

	public Project() {
		this.creation_Time=System.currentTimeMillis();
	}

}
