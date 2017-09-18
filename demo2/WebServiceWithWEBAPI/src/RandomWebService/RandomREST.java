/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import clientToSOAPws.SOAPwsClient;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import net.webservice_distance.LengthUnit;
import net.webservice_distance.LengthUnitSoap;
import net.webservice_distance.Lengths;

/**
 * REST Web Service
 */
@Path("random")
public class RandomREST {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of RandomREST
     */
    public RandomREST() {
    }

    /**
     * Retrieves representation of an instance of RandomWebService.RandomREST
     * @return a random length in feet and metres.
     */
    @GET
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Double[] getJson() {
        try {
            double random = RandomWebServiceClient.randomNumber(0, 1000);
            LengthUnit lengthUnit = new LengthUnit();
            LengthUnitSoap lengthUnitSOAP = lengthUnit.getLengthUnitSoap();
            double lengthInMeters = lengthUnitSOAP.changeLengthUnit(random, Lengths.FEET, Lengths.METERS);
            return new Double[] { random, lengthInMeters };
//            return new JSONArray(Arrays.asList(new Double[] { random, lengthInMeters }));
        } catch (Exception ex) {
            Logger.getLogger(SOAPwsClient.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
        // TODO err code
    }
}
