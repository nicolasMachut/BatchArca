package com.arca.app.dao;

import com.arca.app.utils.MongoDbConnector;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by nicolas on 27/02/16.
 */
public class MongoDbDao implements DAO<Document, Document> {

    private final MongoCollection collection;

    public MongoDbDao (String collectionName) {
        this.collection = MongoDbConnector.INSTANCE.getCollection(collectionName);
    }


    @Override
    public void save(Document data) {
        this.collection.insertOne(data);
    }

    @Override
    public Document getOne(Document data) {
        return (Document) this.collection.find(data).first();
    }

    public Document getLastDocument () {
        return (Document) this.collection.find(new Document()).sort(new Document("lineNumber", -1)).first();
    }

    public void deleteMany (Document document) {
        this.collection.deleteMany(document);
    }
}
