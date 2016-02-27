package dao;

import com.mongodb.client.MongoCollection;

/**
 * Created by nicolas on 27/02/16.
 */
public class ArcaFileDao extends MongoDbDao {
    public ArcaFileDao(MongoCollection collection) {
        super(collection);
    }
}
