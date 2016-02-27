import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import dao.MongoDbDao;
import org.bson.Document;

import java.util.Date;

/**
 * Created by nicolas on 25/02/16.
 */
public class Main {

    public static void main(String[] args) {

        MongoCollection arcaCollection = MongoDbConnector.getCollection();

        MongoDbDao dao = new MongoDbDao(arcaCollection);

        Document res = dao.get(new Document("Batch", "ArcaFile")
                .append("end", new BasicDBObject("$exists", false)));

        int startLine = 0;

        if (res == null) {
            System.out.println("on recommence");

            // Clearing the collection
            arcaCollection.deleteMany(new Document());

            Document document = new Document("Batch", "ArcaFile")
                    .append("start", new Date().getTime());

            dao.save(document);

        } else {
            System.out.println("on reprend");
            Document doc = dao.getLastDocument();
            startLine = Integer.valueOf(doc.get("lineNumber").toString());
        }

        System.out.println("start line : " + startLine);
        String filePath = "/home/nicolas/Bureau/ARCA/data.txt";

        dao.updateOne();
        //new FileHandler(filePath, dao, startLine).handleFile();
    }
}
