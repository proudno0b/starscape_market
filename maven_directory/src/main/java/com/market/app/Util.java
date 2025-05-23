im
import com.fasterxml.jackson.*;
//mport com.fasterxml.jackson.core.ObjectCodec.*;
import java.io.*;
import java.util.*;
public class Util {
    //adapted from medium article
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    public static List<Object> toList(InputStream inputStream) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, new TypeReference<>() {});
        } catch (IOException e) {
            System.out.println("there was an IO exception in util tolist method");
            e.printStackTrace();
        }
    }
}
