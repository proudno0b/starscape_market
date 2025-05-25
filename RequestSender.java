//package starscape_market;
import java.net.http.*;
import java.util.*;
import java.io.*;
//import Gson.jar;
//import java.net.http.URI;
import java.net.URI;

public class RequestSender {
    //boolean exists;
    public RequestSender() {
    }
    public HttpResponse FetchItemData(String item) {
        String api_key = new Secrets().getAPIKey();
        HttpClient client = HttpClient.newHttpClient();
        try {

            HttpRequest request = HttpRequest.newBuilder()
            .uri(new URI(String.format("https://api.v-io.info/v1/market/latest?items=%s",item)))
            .header("x-api-key",api_key)
            .GET()
            .build();
            HttpResponse<InputStream> response = client.send(request,HttpResponse.BodyHandlers.ofInputStream());
            if (response.statusCode() == 200) {
                // do something
                //List<>
            } else {
                System.out.println("Request was bad " + response.statusCode());
            }
            System.out.println("request " + request);
            System.out.println("response " + response);
            //Gson gson = new Gson();
            //MarketItem output = gson.fromJson(response);
            //System.out.println(output)
            return response;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        

        return null;
    }
    public void FetchMarketData() {

    }
    public void ItemToFile(String item) {
        InputStream r = FetchItemData(item);
        try {
            PrintWriter writer = new PrintWriter(item);
            Scanner processor = new Scanner(r);
            while (processor.hasNextLine()) {
                System.out.println(processor.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
