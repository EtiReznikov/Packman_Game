package Algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import Coords.MyCoords;
import GIS.Fruit;
import GIS.Game;
import GIS.Metadata;
import GIS.Packman;

/**
 * This class contains the shortes path of every Packman 
 * @author Ester Reznikov and Chen Ostrovski
 *
 */
public class ShortestPathAlgoold {
	/*
	public static void main(String[] args) {
		Game game=new Game();
		game.ReadCSV("game_1543684662657.csv");
		ShortestPathAlgo test=new ShortestPathAlgo(game);
		test.ShortestPath();

	}
*/

	Game game;
	public ArrayList<Fruit> existing_fruits;
	public ArrayList<Packman> packmen;
	static MyCoords mycoords;
	static int status;

	public ShortestPathAlgoold(Game game){
		this.game=game;
		existing_fruits=new ArrayList<Fruit>(game.fruits);
		packmen=new ArrayList<Packman>(game.packmen);
		mycoords= new MyCoords();
	}
	public Solution ShortestPath() {
		int counter=0;
		System.out.println("start");
		for (Fruit f: existing_fruits) 
			System.out.println(f.getId()+" : "+f.toString());
		System.out.println();
		Solution solution=new Solution();
		if (!this.packmen.isEmpty()) {

			currentPackman();
			Packman fastest;
			//	fastest=fastest(game.packmen);
			Packman nextfastest;
			long twoFruitstime=0;
			while (!this.existing_fruits.isEmpty()) {
				//		ArrayList<Packman> withoutFastet=new ArrayList<>(this.packmen);
				fastest=fastest(game.packmen);
				System.out.println(" befor update fastest id: "+fastest.getId()+" closest: "+fastest.getClosestFruit().getFruit().toString());
				updateFastest(fastest);
				//		existing_fruits.remove(fastest.getClosestFruit().getFruit());
				/*	System.out.println("fastet:"+(++counter));
				for (Fruit f: existing_fruits)
					System.out.println(f.getId()+" : "+f.toString());
				System.out.println();
				 */
				System.out.println(" after update fastest id: "+fastest.getId()+" closest: "+fastest.getClosestFruit().getFruit().toString());
				long time=fastest.getClosestFruit().getTime();
				for (Packman p: this.packmen) {
					if (p.getClosestFruit().getFruit().getLocationInPixle().equals(fastest.getClosestFruit().getFruit().getLocationInPixle())) {
						closestFruit(p);
					}
					closestFruit(fastest);
					twoFruitstime=time+fastest.getClosestFruit().getTime();

				}
				boolean flag=true;
				//	withoutFastet.remove(fastest);
				/*
				if (withoutFastet.size()==0)
					flag=false;
				if (withoutFastet.size()==1)
					nextfastest=withoutFastet.get(0);
				else 
					nextfastest=fastest(withoutFastet);

				 */
				nextfastest=fastest;
				while (nextfastest.getClosestFruit().getTime()<=twoFruitstime && !this.existing_fruits.isEmpty()) {
					if (!nextfastest.equals(fastest)){
						if (existing_fruits.contains(nextfastest.getClosestFruit().getFruit())) {
							updateFastest(nextfastest);
							existing_fruits.remove(nextfastest.getClosestFruit().getFruit());
							//			withoutFastet.remove(nextfastest);
							/*	System.out.println("nextfastest"+(++counter));
						for (Fruit f: existing_fruits)
							System.out.println(f.getId()+" : "+f.toString());
						System.out.println();
							 */
						}
						for (Packman p: this.packmen) {
							if (p.getClosestFruit().getFruit().equals(nextfastest.getClosestFruit().getFruit())) {
								closestFruit(p);
							}
							closestFruit(nextfastest);

							nextfastest=fastest(game.packmen);
							updateFastest(nextfastest);
						}
					}

				}
				if (!existing_fruits.contains(fastest.getClosestFruit().getFruit())) {
					closestFruit(fastest);
				}
			}
			for (Packman p: this.packmen) {
				p.getPath().getPackman().setPackmanLocation(p.getPath().getStartPoint());
				solution.add(p.path);
			}
		}
		/*
		long t=0;
		while (t<=time) {
			for (Packman p: this.packmen) {
				if (p.getClosestFruit().getFruit().equals(nextFruit))
					if (p.getClosestFruit().getTime()<fastest.getClosestFruit().getTime()+time)
						if (p.getClosestFruit().getTime()<nextfastest.getClosestFruit().getTime())
							nextfastest=p;
			}
			t=nextfastest.getClosestFruit().getTime();
			updateFastest(nextfastest);
			existing_fruits.remove(fastest.getClosestFruit().getFruit());
		//	closestFruit(nextfastest);
			for (Packman p: this.packmen) {
				if (p.getClosestFruit().getFruit().equals(nextfastest.getClosestFruit().getFruit())) {
					closestFruit(p);
				}

			}
		 */

		return solution;

	}
	public void updateFastest(Packman fastest) {
		fastest.setGarde(fastest.getGarde()+1);
		fastest.path.add(new Metadata(fastest.getClosestFruit()));
		fastest.getPath().setTime(fastest.getPath().getTime()+fastest.getClosestFruit().getTime());
		fastest.setPackmanLocation(fastest.getClosestFruit().getPoint());
	}


	/*
	public void Thread(Packman fastest) {
		Thread thread= new Thread() {
			@Override
			public void run() {
				if (!fastest.getClosestFruit().getFruit().equals(null)) {
					synchronized(fastest.getClosestFruit().getFruit()) {
						fastest.setGarde(fastest.getGarde()+1);
						fastest.path.add(new Metadata(fastest.getClosestFruit()));
						fastest.getPath().setTime(fastest.getPath().getTime()+fastest.getClosestFruit().getTime());
						fastest.setPackmanLocation(fastest.getClosestFruit().getPoint());
						existing_fruits.remove(fastest.getClosestFruit().getFruit());

					}

				}


			}
		};
		thread.start();
	}
	 */

	/*
		game.packmen.sort(new Packman_Comperator());
		this.packmen.sort(new Packman_Comperator());
		int i=0;
		for (Packman pac: game.packmen) {

			this.packmen.get(i).getPath().getPackman().setPackmanLocation(pac.getPackmanLocation());

			pac.path=this.packmen.get(i).path;
			i++;
		}
	 */






	public void closestFruit(Packman packman){
		long time, minTime;
		double distance;
		Fruit closest=null;


		Iterator<Fruit> iter=this.existing_fruits.iterator();
		if (iter.hasNext()){
			closest=iter.next();
			distance=mycoords.distance3d(packman.getPackmanLocation(), closest.getFruitLocation());
			minTime=timeCalculate(distance, packman.getVelocity());	
			packman.setClosestFruit(closest, minTime);
			while (iter.hasNext()) {
				Fruit current=iter.next();
				distance=mycoords.distance3d(packman.getPackmanLocation(), current.getFruitLocation());
				time=timeCalculate(distance, packman.getVelocity());	
				if (time<minTime ) {
					closest=current;
					minTime=time;
				}
			}
			packman.setClosestFruit(  closest, minTime*1000);
		}


	}
	public long timeCalculate(double distance, long velocity) {
		return (long) (distance/velocity);
	}

	public  void currentPackman() {
		for (Packman p: this.packmen)
			closestFruit(p);
	}
	public  Packman fastest(ArrayList<Packman> ExistingPackmem) {
		Packman fastest= ExistingPackmem.get(0);
		for (Packman p: this.packmen) {
			if (p.getClosestFruit().getTime()<fastest.getClosestFruit().getTime())
				if (p.getClosestFruit().getTime()!=-1)
					fastest=p;
		}
		return fastest;

	}


	/*
		for (Packman pac: this.packmen) {
			if (pac.getClosestFruit().getTime()<fastest.getClosestFruit().getTime() )
				if (pac.getClosestFruit().getTime()!=-1)
					fastest=pac;
		}
		return fastest;
	}
	 */
}

