# Demo 1 - Runway length app

Fetches airport runway lengths in metres.

1. User inputs target city/airport name in the frontend
2. Request gets parsed in the servlet; given airport is queried from the Airport Information Webservice
3. Runway length is parsed from the airport information and converted into metres via the Length/Distance Unit Convertor Webservice
4. Converted runway length is returned back to the frontend

The main logic is implemented at [`SOAPwsClient.java`](ServletClientToRemoteSOAPws/src/clientToSOAPws/SOAPwsClient.java).
