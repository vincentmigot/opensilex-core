//******************************************************************************
//                             OpensilexTest.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: morgane.vidal@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;

/**
 * The application test class. Initialize the required environment for the tests. 
 * Any other test class must extends OpensilexTest.
 * @author Morgane Vidal <morgane.vidal@inra.fr>
 */
public class OpensilexTest extends JerseyTest {
    
    /**
     * Initialise the required environment for the tests with the list of packages.
     * @param clazz the class to configure
     * @return 
     */
    protected ResourceConfig configure(Class<?> clazz) {
        ResourceConfig resourceConfig = new ResourceConfig(clazz);
        
        resourceConfig.packages("io.swagger.jaxrs.listing;"
                + "phis2ws.service.resources;"
                + "phis2ws.service.json;"
                + "phis2ws.service.resources.request.filters");
        
        return resourceConfig;
    }
}
