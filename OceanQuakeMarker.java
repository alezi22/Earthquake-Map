package module5;

import de.fhpotsdam.unfolding.data.PointFeature;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.utils.ScreenPosition;

import java.util.*;

import de.fhpotsdam.unfolding.UnfoldingMap;

/** Implements a visual marker for ocean earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class OceanQuakeMarker extends EarthquakeMarker {
	
	private UnfoldingMap locationMap;
	private List<Marker> threatenedCityMarkers;
	private EarthquakeCityMap eqcm;
	//private List<Marker> tcm = new ArrayList<Marker>();

	public OceanQuakeMarker(PointFeature quake, UnfoldingMap map) {
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = false;
		locationMap = map;		
	}
	

	/** Draw the earthquake as a square */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {		
		pg.rect(x-radius, y-radius, 2*radius, 2*radius);		
		/*if(this.getClicked()) {
			//tcm = eqcm.threatenedCityMarkers;
			pg.stroke(0);	
			for(Marker m : EarthquakeCityMap.threatenedCityMarkers) {
				ScreenPosition sp = locationMap.getScreenPosition(m.getLocation());
				//lineBetweenMarker(pg, x, y, m);
				pg.line(x, y, ((CityMarker)m).accurateX, ((CityMarker)m).accurateY);
				//pg.line(x, y, sp.x, sp.y);
			}
			//pg.stroke(255,0,0);
			//pg.fill(0);
			//pg.strokeWeight(10);
			//pg.line(x, y, x+100, y+100);
		} else {
									
		}*/
		
		//pg.noStroke();
		//pg.stroke(0);
		//pg.fill(0);
		//pg.strokeWeight(10);
		//pg.line(x, y, x+100, y+100);
		
		/**/
		
	}
	
	public void threateningLines(PGraphics pg, float x, float y) {
		if (threatenedCityMarkers == null){
			threatenedCityMarkers = new ArrayList<Marker>();	
		}
		threatenedCityMarkers.clear();
		for(Marker mh : EarthquakeCityMap.cityMarkers) {
			if(mh.getDistanceTo(this.getLocation()) <= this.threatCircle()) {
				if (threatenedCityMarkers.indexOf(mh) == -1) {
					threatenedCityMarkers.add(mh);				
				}
			} 
		}
		if(threatenedCityMarkers != null && this.getClicked()) {			
			for(Marker m : threatenedCityMarkers) {
				//pg.pushStyle();
				pg.line(x, y, ((CityMarker)m).accurateX, ((CityMarker)m).accurateY);
				//pg.popStyle();
			}
		}
	}

	/*private void lineBetweenMarker(PGraphics pg, float x, float y, Marker m) {
		// TODO Auto-generated method stub
		float accurateX = ((CityMarker)m).accurateX;
		float accurateY = ((CityMarker)m).accurateY;
		pg.line(x, y, accurateX, accurateY);
	}*/	

	/*private ScreenPosition getScreenPosition(Marker marker){
		return locationMap.getScreenPosition(marker.getLocation());
	}*/

}
