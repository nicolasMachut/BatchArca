import dao.DAO;
import org.bson.BsonDateTime;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

/**
 * Created by nicolas on 27/02/16.
 */
public class FileHandler {

    private int startLine;
    private DAO dao;
    private Path path;
    private int currentLine;

    public FileHandler(String filePath, DAO dao, int startLine) {
        this.path = Paths.get(filePath);
        this.dao = dao;
        this.startLine = startLine;
    }

    public void handleFile () {

        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> handleLine(s));



        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleLine(String line) {
        if (++currentLine < startLine) {
            return;
        }

        String[] splittedLine = line.split(",");
        String timestamp = splittedLine[0];
        int value = Integer.valueOf(splittedLine[1]);
        String country = splittedLine[2];
        Document document = new Document("timestamp", new Date(Long.valueOf(timestamp)))
                .append("value",value)
                .append("country", country)
                .append("lineNumber", currentLine);
        dao.save(document);
    }
}
