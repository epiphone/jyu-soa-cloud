package clientToSOAPws;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.webservice_airport.Airport;
import net.webservice_airport.AirportSoap;
import net.webservice_distance.LengthUnit;
import net.webservice_distance.LengthUnitSoap;
import net.webservice_distance.Lengths;

public class SOAPwsClient {

	public SOAPwsClient(){}
	
	public String sendRequest(String str) throws URISyntaxException, IOException {	
                Airport airport = new Airport();
                AirportSoap airportSOAP = airport.getAirportSoap();
                String airportInfo = airportSOAP.getAirportInformationByCityOrAirportName(str);
                int runwayLength = this.parseRunwayLength(airportInfo);
                if (runwayLength < 0) {
                    return null;
                }
                
                LengthUnit lengthUnit = new LengthUnit();
                LengthUnitSoap lengthUnitSOAP = lengthUnit.getLengthUnitSoap();
                double lengthInMeters = lengthUnitSOAP.changeLengthUnit(runwayLength, Lengths.FEET, Lengths.METERS);
                return ""+lengthInMeters;
	}
        
        public static int parseRunwayLength(String airportInfo) {
            Pattern r = Pattern.compile("<RunwayLengthFeet>(\\d+)</RunwayLengthFeet>");
            Matcher m = r.matcher(airportInfo);
            if (m.find()) {
                return Integer.parseInt(m.group(1));
            } else {
                return -1;
            }
        }
	
}
