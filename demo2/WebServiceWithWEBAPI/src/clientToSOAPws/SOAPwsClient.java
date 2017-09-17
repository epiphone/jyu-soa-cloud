package clientToSOAPws;

import java.io.IOException;
import java.net.URISyntaxException;

import net.webservice_distance.LengthUnit;
import net.webservice_distance.LengthUnitSoap;
import net.webservice_distance.Lengths;
import RandomWebService.RandomWebServiceClient;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SOAPwsClient {

	public SOAPwsClient(){}
	
	public String sendRequest(String str) throws URISyntaxException, IOException {	
                double random;
            try {
                random = RandomWebServiceClient.randomNumber(0, 1000);
                LengthUnit lengthUnit = new LengthUnit();
                LengthUnitSoap lengthUnitSOAP = lengthUnit.getLengthUnitSoap();
                double lengthInMeters = lengthUnitSOAP.changeLengthUnit(random, Lengths.FEET, Lengths.METERS);
//                return ""+lengthInMeters;
                return ""+random;
            } catch (Exception ex) {
                Logger.getLogger(SOAPwsClient.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            return "fail";
	}
}
