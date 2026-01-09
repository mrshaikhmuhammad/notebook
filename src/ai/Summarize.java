package ai;

import java.io.IOException;
import java.net.URI;
import java.net.http.*;

public class Summarize {
    private final static String URL = "https://router.huggingface.co/hf-inference/models/facebook/bart-large-cnn";
    private final static String TOKEN = "";

    // xyz     -->     {"input":"xyz"}
    private static String toJson(String input){
        return String.format("{\"inputs\": \"%s\"}", input.replace("\"", "\\\""));
    }

    // {"result":"xyz"}    -->     xyz
    private static String fromJson(String input){ 
        int start = input.indexOf(":") + 2;
        int end = input.lastIndexOf("\"");
        return input.substring(start, end);
    }

    public static String summarize(String input) throws IOException, InterruptedException{
        //Create HTTP Request 
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(URL))
                .header("Authorization", "Bearer " + TOKEN)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(toJson(input)))
                .build();

        //Recieve HTTP Responce
        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String output = response.body();

        //Return Output
        return fromJson(output);
    }
}
