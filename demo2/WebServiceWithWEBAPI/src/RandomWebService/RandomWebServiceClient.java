/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import RandomWebService.RandomWebService;

public class RandomWebServiceClient {
    public static double randomNumber(double min, double max) throws Exception {
//	URL url = new URL("http://localhost:8084/WebServiceWithWEBAPI/RandomWebServiceImpl?wsdl");
        URL url = new URL("https://ties456-demo2.herokuapp.com/RandomWebServiceImpl?wsdl");
        QName qname = new QName("http://RandomWebService/", "RandomWebServiceImplService");
        Service service = Service.create(url, qname);

        RandomWebService randomWS = service.getPort(RandomWebService.class);
        return randomWS.randomNumber(min, max);
    }
}