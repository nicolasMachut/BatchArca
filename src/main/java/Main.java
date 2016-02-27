import com.mongodb.BasicDBObject;
import dao.ArcaBatchDao;
import dao.ArcaFileDao;
import dao.DAO;
import dao.MongoDbDao;
import org.bson.Document;

import java.util.Date;

/**
 * Created by nicolas on 25/02/16.
 */
public class Main {

    public static void main(String[] args) {

        MongoDbDao arcaFileDao = new ArcaFileDao(MongoDbConnector.INSTANCE.getCollection("arcaFileDao"));
        MongoDbDao arcaBatchDao = new ArcaBatchDao(MongoDbConnector.INSTANCE.getCollection("arcaBatch"));

        Document res = arcaBatchDao.get(new Document("Batch", "ArcaFile")
                .append("end", new BasicDBObject("$exists", false)));

        int startLine = 0;

        if (res == null) {
            System.out.println("on recommence");

            // Clearing the collection
            arcaFileDao.deleteMany(new Document());
            arcaBatchDao.deleteMany(new Document());

            Document document = new Document("Batch", "ArcaFile")
                    .append("start", new Date().getTime());

            arcaFileDao.save(document);

        } else {
            System.out.println("on reprend");
            Document doc = arcaFileDao.getLastDocument();
            startLine = Integer.valueOf(doc.get("lineNumber").toString());
        }

        System.out.println("start line : " + startLine);
        String filePath = "/home/nicolas/Bureau/ARCA/data.txt";

        arcaFileDao.updateOne();
        //new FileHandler(filePath, arcaFileDao, startLine).handleFile();
    }
}
