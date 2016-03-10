package com.arca.app.batch;

import com.arca.app.dao.ArcaBatchDao;
import com.arca.app.dao.ArcaFileDao;
import org.bson.Document;

import java.util.Date;

public class BatchWork {

    private ArcaFileDao fileDao;
    private ArcaBatchDao batchDao;

    public BatchWork (){
        this.fileDao = new ArcaFileDao();
        this.batchDao = new ArcaBatchDao();
    }

    public void startBatch() {

        Document currentBatch = batchDao.getCurrentBatch();

        int currentLine = 0;

        if (noCurrentBatch(currentBatch)) {
            creationOfANewBatch();

        } else {
            currentLine = recoveryOfTheCurrentBatch();
        }

        System.out.println("start line : " + currentLine);
        String filePath = "C:/Users/machu/Downloads/data.txt";

        new FileHandler(filePath, currentLine).handleFile();
    }

    private boolean noCurrentBatch(Document currentBatch) {
        return currentBatch == null;
    }

    private int recoveryOfTheCurrentBatch() {
        int startLine = 0;
        System.out.println("Reprise du batch");
        Document doc = fileDao.getLastDocument();
        if (doc != null){
            startLine = getLineNumber(doc);
        }
        return startLine;
    }

    private int getLineNumber(Document doc) {
        return Integer.valueOf(doc.get("lineNumber").toString());
    }

    private void creationOfANewBatch() {
        System.out.println("Démarrage du batch");

        // Clearing collections
        fileDao.deleteMany(new Document());
        batchDao.deleteMany(new Document());

        // Create a new batch document in bdd
        batchDao.saveBatch(new Date().getTime());
    }
}
