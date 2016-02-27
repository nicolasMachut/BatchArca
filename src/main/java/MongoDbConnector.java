import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by nicolas on 27/02/16.
 */
public class MongoDbConnector {

    private MongoDbConnector () {

    }

    public static synchronized MongoCollection getCollection () {
        // Creating mongodb uri connection
        MongoClientURI uri = new MongoClientURI("mongodb://arcaBatch:arca@ds017248.mlab.com:17248/arcadb");

        // Creating mongodb connection
        MongoClient mongoClient = new MongoClient(uri);

        // getting mongodb database
        MongoDatabase database = mongoClient.getDatabase(uri.getDatabase());

        // Getting mongodbCollection
        return database.getCollection("arcaFile");
    }
}
