//******************************************************************************
//                          RDF4JService.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core.sparql;

import org.opensilex.core.config.RDF4JConfig;

/**
 * Implementation of SPARQLService for RDF4J
 */
public class RDF4JService implements SPARQLService {
    
    /**
     * RDF4J connection configuration
     */
    private RDF4JConfig config;
    
    /**
     * Constructor for RDF4J service with configuration
     * 
     * @param config RDF4J connection configuration
     */
    public RDF4JService(RDF4JConfig config) {
        this.config = config;
    }
}
