//******************************************************************************
//                      ApplicationServiceBinder.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

import org.opensilex.core.config.ApplicationCoreConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.opensilex.core.config.MongoDBConfig;
import org.opensilex.core.config.RDF4JConfig;
import org.opensilex.core.mongodb.MongoDBServiceImpl;
import org.opensilex.core.sparql.RDF4JService;
import org.opensilex.core.sparql.SPARQLService;
import org.opensilex.core.mongodb.MongoDBService;

/**
 * This class bind all services used by core module according to a given configuration
 */
public class ApplicationServiceBinder extends AbstractBinder {

    /**
     * Core application configuration
     */
    private ApplicationCoreConfig config;
    
    /**
     * RDF4J configuration
     */
    private RDF4JConfig configRDF4J;
    
    /**
     * MongoDB configuration
     */
    private MongoDBConfig configMongoDB;
    
    /**
     * Constructor with config
     * @param config Application configuration
     * @param configRDF4J
     * @param configMongoDB 
     */
    ApplicationServiceBinder(ApplicationCoreConfig config, RDF4JConfig configRDF4J, MongoDBConfig configMongoDB) {
        this.config = config;
        this.configRDF4J = configRDF4J;
        this.configMongoDB = configMongoDB;
    }

    @Override
    protected void configure() {
        // Initialize SPARQLService
        bind(new RDF4JService(configRDF4J)).to(SPARQLService.class);
        
        // Initialize MongoDB Service
        bind(new MongoDBServiceImpl(configMongoDB)).to(MongoDBService.class);
    }
}
