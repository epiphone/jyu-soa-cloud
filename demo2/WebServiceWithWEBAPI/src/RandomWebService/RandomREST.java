/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import net.webservice_distance.LengthUnit;
import net.webservice_distance.LengthUnitSoap;
import net.webservice_distance.Lengths;

/**
 * REST Web Service
 */
@Path("distances")
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
    public Double[] getJson(@DefaultValue("0") @QueryParam("min") double min, @DefaultValue("1000") @QueryParam("max") double max) {
        if (min > max) {
            throw new WebApplicationException(Response.status(400).entity("max should be smaller than min").build());
        }
        
        try {
            double randomMetres = RandomWebServiceClient.randomNumber(min, max);
            LengthUnit lengthUnit = new LengthUnit();
            LengthUnitSoap lengthUnitSOAP = lengthUnit.getLengthUnitSoap();
            double randomFeet = lengthUnitSOAP.changeLengthUnit(randomMetres, Lengths.METERS, Lengths.FEET);
            return new Double[] { randomMetres, randomFeet };
        } catch (Exception ex) {
            return null;
        }
    }
}
