/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RandomWebService;

import javax.xml.ws.Endpoint;
import RandomWebService.RandomWebServiceImpl;

//Endpoint publisher
public class RandomWebServicePublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:9999/ws/random", new RandomWebServiceImpl());
    }
}