import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Created by nicolas on 25/02/16.
 */
public class Main {

    public static MongoCollection arcaFile;

    public static void main(String[] args) {

        MongoClientURI uri = new MongoClientURI("mongodb://arcaBatch:arca@ds017248.mlab.com:17248/arcadb");
        MongoClient mongoClient = new MongoClient(uri);
        MongoDatabase database = mongoClient.getDatabase(uri.getDatabase());
        arcaFile = database.getCollection("arcaFile");

        String filePath = "/home/nicolas/Bureau/ARCA/data.txt";

        Path path = Paths.get(filePath);
        try (Stream<String> lines = Files.lines(path)) {
            long linesCount = lines.count();

            System.out.println(linesCount);

            //lines.forEach(s -> readALine(s));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.exit(0);
    }

    public static void readALine(String line) {
        String[] splittedLine = line.split(",");
        String timestamp = splittedLine[0];
        String value = splittedLine[1];
        String country = splittedLine[2];
        saveLineInBdd(timestamp, value, country);
    }

    private static void saveLineInBdd(String timestamp, String value, String country) {
        Document document = new Document("timestamp", timestamp).
                append("value",value).
                append("country", country);
        arcaFile.insertOne(document);
    }
}
