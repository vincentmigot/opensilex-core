//******************************************************************************
//                        MongoDBConfig.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core.config;

/**
 * MongoDB configuration interface
 */
public interface MongoDBConfig {

    /**
     * MongoDB server host name or IP address
     *
     * @return Host or IP address
     */
    String host();

    /**
     * MongoDB server port
     *
     * @return Port
     */
    int port();

    /**
     * MongoDB database name
     *
     * @return Database name
     */
    String database();
}
