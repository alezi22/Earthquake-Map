package module5;

import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import processing.core.PGraphics;

/** Implements a visual marker for land earthquakes on an earthquake map
 * 
 * @author UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 *
 */
public class LandQuakeMarker extends EarthquakeMarker {
	
	private List<Marker> threatenedCityMarkers;
	//private EarthquakeCityMap eqcm;
	
	public LandQuakeMarker(PointFeature quake) {
		
		// calling EarthquakeMarker constructor
		super(quake);
		
		// setting field in earthquake marker
		isOnLand = true;
	}


	/** Draw the earthquake as an ellipse */
	@Override
	public void drawEarthquake(PGraphics pg, float x, float y) {
		pg.ellipse(x, y, 2*radius, 2*radius);
		
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

	// Get the country the earthquake is in
	public String getCountry() {
		return (String) getProperty("country");
	}

		
}