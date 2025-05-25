import com.fasterxml.jackson.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;
public class Util {
    //adapted from medium article
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static MarketItem toList(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, MarketItem.class);
        } catch (IOException e) {
            System.out.println("there was an IO exception in util tolist method");
            e.printStackTrace();
        }
    }
}
