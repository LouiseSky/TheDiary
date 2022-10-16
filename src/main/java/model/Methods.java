package model;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Methods {
   public static void Json() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Path pathOne = Paths.get("src/main/resources/users.json");
        List.of(mapper.readValue(pathOne.toFile(), User[].class));
    }
}
