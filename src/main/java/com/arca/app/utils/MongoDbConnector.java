package com.arca.app.utils;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Created by nicolas on 27/02/16.
 */
public class MongoDbConnector {

    private MongoDatabase database;

    // It's a singleton ;)
    public final static MongoDbConnector INSTANCE = new MongoDbConnector();

    private MongoDbConnector () {

        // Connection properties
        String databaseName = "arcadb";
        String host = "ds017248.mlab.com";
        String user = "arcaBatch";
        String password = "arca";
        int port = 17248;

        // Creating mongodb uri connection
        MongoClientURI uri = new MongoClientURI("mongodb://" + user + ":" + password + "@" + host + ":" + port + "/" + databaseName);

        // Creating mongodb connection
        MongoClient mongoClient = new MongoClient(uri);

        // getting mongodb database
        database = mongoClient.getDatabase(uri.getDatabase());
    }

    public MongoCollection getCollection (String collectionName) {
        return database.getCollection(collectionName);
    }
}
