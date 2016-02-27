package dao;

import com.mongodb.client.MongoCollection;

/**
 * Created by nicolas on 27/02/16.
 */
public class ArcaBatchDao extends MongoDbDao {

    public ArcaBatchDao(MongoCollection collection) {
        super(collection);
    }
}
