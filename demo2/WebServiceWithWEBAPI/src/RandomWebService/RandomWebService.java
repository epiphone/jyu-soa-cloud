/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author aleksi
 */
@WebService(serviceName = "RandomWebService")
public class RandomWebService {
    
    @WebMethod(operationName = "randomNumber")
    public Double randomNumber(@WebParam(name = "min") double min, @WebParam(name = "max") double max) {
        return Math.random() * max + min;
    }
}
