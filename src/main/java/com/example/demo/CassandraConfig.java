package com.example.demo;


import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories(basePackages = "com.example")
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Override
    protected String getContactPoints(){
        return "127.0.0.1";
    }

    @Override
    protected String getLocalDataCenter(){
        return "datacenter1";
    }

    @Override
    protected int getPort(){
        return 9042;
    }

    @Override
    protected String getKeyspaceName() {
        return "test_cassandra";
    }

    @Override
    protected String getClusterName(){return "Test Cluster";}
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.CREATE_IF_NOT_EXISTS;
    }



}
