/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import javax.jws.WebService;

@WebService(endpointInterface = "RandomWebService.RandomWebService")
public class RandomWebServiceImpl implements RandomWebService {
    
    @Override
    public Double randomNumber(double min, double max) {
        return Math.random() * max + min;
    }
}
