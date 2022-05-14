package com.bot.demo.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Collection;
import java.util.Collections;

@Configuration
@EnableMongoRepositories(basePackages = "com.bot.demo")
public class MongoConfig extends AbstractMongoClientConfiguration {
    @Value("${spring.data.mongodb.username}")
    private String username;
    @Value("${spring.data.mongodb.password}")
    private String password;
    @Value("${spring.data.mongodb.database}")
    private String database;

    @Override
    protected String getDatabaseName() {
        return database;
    }

    @Override
    public MongoClient mongoClient() {
        final ConnectionString connectionString = new ConnectionString("mongodb://"+username+":"+password+"@selfmanagebotcluster-shard-00-00.mvecp.mongodb.net:27017,selfmanagebotcluster-shard-00-01.mvecp.mongodb.net:27017,selfmanagebotcluster-shard-00-02.mvecp.mongodb.net:27017/myFirstDatabase?ssl=true&replicaSet=atlas-yis6z3-shard-0&authSource=admin&retryWrites=true&w=majority");
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Override
    public Collection<String> getMappingBasePackages() {
        return Collections.singleton("com.bot");
    }
}
