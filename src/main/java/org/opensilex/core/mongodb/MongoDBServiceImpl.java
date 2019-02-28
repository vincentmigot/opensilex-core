//******************************************************************************
//                        MongoDBServiceImpl.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core.mongodb;

import org.opensilex.core.config.MongoDBConfig;

/**
 * Implementation of MongoDBService
 */
public class MongoDBServiceImpl implements MongoDBService {
        
    /**
     * MongoDB connection configuration
     */
    private MongoDBConfig config;
    
    /**
     * Constructor for MongoDB service with configuration
     * 
     * @param config MongoDB connection configuration
     */
    public MongoDBServiceImpl(MongoDBConfig config) {
        this.config = config;
    }
    
    
}
