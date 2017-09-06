package clientToSOAPws;

import java.io.IOException;
import java.net.URISyntaxException;

import net.webservicex.ConvertTemperature;
import net.webservicex.ConvertTemperatureSoap;
import net.webservicex.TemperatureUnit;

public class SOAPwsClient {

	public SOAPwsClient(){}
	
	public String sendRequest(String str) throws URISyntaxException, IOException {	
	
		double value = Double.parseDouble(str); // set the value;
		ConvertTemperature tempConvService = new ConvertTemperature();  // create service object;
		ConvertTemperatureSoap tempConvertServiceSOAP = tempConvService.getConvertTemperatureSoap();  // create SOAP object (a port of the service);
		double resValue = tempConvertServiceSOAP.convertTemp(value, TemperatureUnit.DEGREE_CELSIUS, TemperatureUnit.DEGREE_FAHRENHEIT);
		
                return "pasksaaa";
//		return ""+resValue;
	
	}
	
}
