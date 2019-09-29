package module5;

import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import processing.core.PConstants;
import processing.core.PGraphics;
import de.fhpotsdam.unfolding.marker.Marker;

import java.util.ArrayList;
import java.util.List;

/** Implements a visual marker for cities on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
// TODO: Change SimplePointMarker to CommonMarker as the very first thing you do 
// in module 5 (i.e. CityMarker extends CommonMarker).  It will cause an error.
// That's what's expected.
public class CityMarker extends CommonMarker {
	
	public float accurateX;
	public float accurateY;
	private List<Marker> threateningQuakeMarkers;
	private EarthquakeCityMap eqcm;
	
	public static int TRI_SIZE = 5;  // The size of the triangle marker
	
	public CityMarker(Location location) {
		super(location);
	}
	
	
	public CityMarker(Feature city) {
		super(((PointFeature)city).getLocation(), city.getProperties());
		// Cities have properties: "name" (city name), "country" (country name)
		// and "population" (population, in millions)
	}

	
	/**
	 * Implementation of method to draw marker on the map.
	 */
	public void drawMarker(PGraphics pg, float x, float y) {
		accurateX = x;
		accurateY = y;
		
		// Save previous drawing style
		pg.pushStyle();
		
		// IMPLEMENT: drawing triangle for each city
		pg.fill(150, 30, 30);
		pg.triangle(x, y-TRI_SIZE, x-TRI_SIZE, y+TRI_SIZE, x+TRI_SIZE, y+TRI_SIZE);
		
		// Restore previous drawing style
		pg.popStyle();
	}
	
	/** Show the title of the city if this marker is selected */
	public void showTitle(PGraphics pg, float x, float y)
	{
		
		// TODO: Implement this method
		String txt = getCity() + "," + getCountry() + ", Population of " + getPopulation() + " million";
		pg.pushStyle();
		
		pg.fill(255);
		pg.noStroke();
		pg.rect(x, y-TRI_SIZE-17, pg.textWidth(txt)+6, 16);
		pg.fill(0);
		pg.strokeWeight(6);
		pg.textSize(12);
		pg.text(txt, x+3, y-3-TRI_SIZE);		
		
		pg.popStyle();
	}
	
	public void threateningLines(PGraphics pg, float x, float y) {
		if (threateningQuakeMarkers == null){
			threateningQuakeMarkers = new ArrayList<Marker>();	
		}
		threateningQuakeMarkers.clear();
		for(Marker mh : EarthquakeCityMap.quakeMarkers) {
			EarthquakeMarker em = (EarthquakeMarker)mh;
			if(em.getDistanceTo(this.getLocation()) <= em.threatCircle()) {
				if (threateningQuakeMarkers.indexOf(mh) == -1) {
					threateningQuakeMarkers.add(mh);				
				}
			} 
		}
		if(threateningQuakeMarkers != null && this.getClicked()) {			
			for(Marker m : threateningQuakeMarkers) {
				//pg.pushStyle();				
				pg.line(x, y, ((EarthquakeMarker)m).accurateX, ((EarthquakeMarker)m).accurateY);
				//pg.popStyle();
			}
		}
	}
	
	/*@Override
	public void showThreat(List<Marker> earthquakeMarkers, List<Marker> cityMarkers){
		// Hiding all the cities except this
		for (Marker cityMarker: cityMarkers){
			if (!this.equals(cityMarker)){
				cityMarker.setHidden(true);
			}
		}
		
		// Hiding the earthquakes which don't effect this cityMarker
		// City not threatened by an earthquake
		// if distance between earthquakeMarker and CityMarker > threatCircle()
		for (Marker earthquakeMarker: earthquakeMarkers){
			double threat = ((EarthquakeMarker) earthquakeMarker).threatCircle();
			if (earthquakeMarker.getDistanceTo(this.getLocation()) > threat){
				earthquakeMarker.setHidden(true);
			}
		}
	}*/
	
	/* Local getters for some city properties.  
	 */
	public String getCity()
	{
		return getStringProperty("name");
	}
	
	public String getCountry()
	{
		return getStringProperty("country");
	}
	
	public float getPopulation()
	{
		return Float.parseFloat(getStringProperty("population"));
	}
}
