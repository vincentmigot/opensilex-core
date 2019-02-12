//******************************************************************************
//                         RDF4JConfig.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core.config;

/**
 * RDF4J configuration interface
 */
public interface RDF4JConfig {

    /**
     * RDF4J server host name or IP address
     *
     * @return Host or IP address
     */
    String host();

    /**
     * RDF4J server port
     *
     * @return Port
     */
    int port();

    /**
     * RDF4J repository name
     *
     * @return Repository name
     */
    String repository();
}
