package dao;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.Date;
import java.util.List;

/**
 * Created by nicolas on 27/02/16.
 */
public class MongoDbDao implements DAO<Document, Document> {

    private final MongoCollection collection;

    public MongoDbDao (MongoCollection collection) {
        this.collection = collection;
    }

    @Override
    public void save(List<Document> data) {
        this.collection.insertMany(data);
    }

    @Override
    public void save(Document data) {
        this.collection.insertOne(data);
    }

    @Override
    public Document get(Document data) {
        return (Document) this.collection.find(data).first();
    }

    public Document getLastDocument () {
        return (Document) this.collection.find(new Document()).sort(new Document("lineNumber", -1)).first();
    }

    public void updateOne () {
        this.collection.updateOne(new Document("Batch", "ArcaFile"), new Document("$set", new Document("end", new Date().getTime())));
    }

    public void deleteMany (Document document) {
        this.collection.deleteMany(document);
    }
}
