package dao;

import com.mongodb.DBObject;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.util.List;

/**
 * Created by nicolas on 27/02/16.
 */
public class MongoDbDao implements DAO<Document> {

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
}
