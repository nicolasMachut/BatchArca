import dao.DAO;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;
import java.util.Date;

/**
 * Created by nicolas on 27/02/16.
 */
public class FileHandler {

    private DAO dao;
    private Path path;
    private int lineListSize = 100;
    private ArrayList<Document> lineList;

    public FileHandler(String filePath, DAO dao) {
        this.path = Paths.get(filePath);
        this.lineList = new ArrayList<>(lineListSize);
        this.dao = dao;
    }

    public void handleFile () {

        Document document = new Document("Batch", "ArcaFile").
                append("start", new Date().getTime());

        this.dao.save(document);


        try (Stream<String> lines = Files.lines(path)) {
            //lines.forEach(s -> handleLine(s));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleLine(String line) {
        System.out.println(line);
        if (this.haveToBeInserted()) {
            String[] splittedLine = line.split(",");
            String timestamp = splittedLine[0];
            String value = splittedLine[1];
            String country = splittedLine[2];
            this.createDocumentAndAddItToList(timestamp, value, country);

        } else {
            this.save();
            this.lineList = new ArrayList<>(lineListSize);
        }
    }

    private void createDocumentAndAddItToList(String timestamp, String value, String country) {
        Document document = new Document("timestamp", timestamp).
                append("value",value).
                append("country", country);
        this.lineList.add(document);
    }

    private boolean haveToBeInserted() {
        return this.lineList.size() == 0 || this.lineList.size() % lineListSize != 0;
    }

    private void save() {
        System.out.println("save in bdd**************************************************************");
        dao.save(lineList);
    }
}
