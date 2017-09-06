package clientToSOAPws;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.webservicex.ConvertTemperature;
import net.webservicex.ConvertTemperatureSoap;
import net.webservicex.TemperatureUnit;
import net.webservice_airport.Airport;
import net.webservice_airport.AirportSoap;
import org.xml.sax.InputSource;

public class SOAPwsClient {

	public SOAPwsClient(){}
	
	public String sendRequest(String str) throws URISyntaxException, IOException {	
                Airport airport = new Airport();
                AirportSoap airportSOAP = airport.getAirportSoap();
                String airportInfo = airportSOAP.getAirportInformationByCityOrAirportName(str);
                String runwayLength = this.parseRunwayLength(airportInfo);
                if (runwayLength != null) {
                    return runwayLength;
                } else {
                    return "vituiks";
                }
            
//		double value = Double.parseDouble(str); // set the value;
//		ConvertTemperature tempConvService = new ConvertTemperature();  // create service object;
//		ConvertTemperatureSoap tempConvertServiceSOAP = tempConvService.getConvertTemperatureSoap();  // create SOAP object (a port of the service);
//		double resValue = tempConvertServiceSOAP.convertTemp(value, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT);
//		
//		return ""+resValue;
	
	}
        
        public static String parseRunwayLength(String airportInfo) {
            Pattern r = Pattern.compile("<RunwayLengthFeet>(\\d+)</RunwayLengthFeet>");
            Matcher m = r.matcher(airportInfo);
            if (m.find()) {
                return m.group(1);
            } else {
                return null;
            }
        }
	
}
