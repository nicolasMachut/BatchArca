import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import dao.MongoDbDao;
import org.bson.Document;

/**
 * Created by nicolas on 25/02/16.
 */
public class Main {

    public static void main(String[] args) {

        MongoCollection arcaCollection = MongoDbConnector.getCollection();

        MongoDbDao dao = new MongoDbDao(arcaCollection);

        Document res = dao.get(new Document("Batch", "ArcaFile"));

        System.out.println(res.get("Batch"));

        // Clearing the collection
        /*arcaCollection.deleteMany(new Document());

        String filePath = "/home/nicolas/Bureau/ARCA/data.txt";

        new FileHandler(filePath, dao).handleFile();*/

    }
}
