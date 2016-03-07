package com.arca.app.dao;

import org.bson.Document;

public class ArcaBatchDao extends MongoDbDao {

    public ArcaBatchDao() {
        super("arcaBatch");
    }

    public Document getCurrentBatch() {
        return this.getOne(new Document("Batch", "ArcaFile")
               .append("end", new Document("$exists", false)));
    }

    public void saveBatch(long time) {
        Document document = new Document("Batch", "ArcaFile")
               .append("start", time);

        this.save(document);
    }
}
