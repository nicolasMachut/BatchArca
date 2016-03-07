package com.arca.app.batch;

import com.arca.app.dao.ArcaFileDao;
import org.bson.Document;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.stream.Stream;

public class FileHandler {

    private int startLine;
    private Path path;
    private int currentLine;
    private ArcaFileDao fileDao;

    public FileHandler(String filePath, int startLine) {
        this.path = Paths.get(filePath);
        this.startLine = startLine;
        this.fileDao = new ArcaFileDao();
    }

    public void handleFile () {

        // Foreach line in the file
        try (Stream<String> lines = Files.lines(path)) {
            lines.forEach(s -> handleLine(s));

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void handleLine(String line) {

        if (!lineHaveToBeHandle()) {
            return;
        }

        String[] splittedLine = line.split(",");
        String timestamp = splittedLine[0];
        int value = Integer.valueOf(splittedLine[1]);
        String country = splittedLine[2];

        saveLine(timestamp, value, country);
    }

    private void saveLine(String timestamp, int value, String country) {
        Document document = new Document("timestamp", new Date(Long.valueOf(timestamp)))
                .append("value",value)
                .append("country", country)
                .append("lineNumber", currentLine);
        fileDao.save(document);
    }

    private boolean lineHaveToBeHandle() {
        return ++currentLine >= startLine;
    }
}
