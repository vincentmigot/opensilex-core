//******************************************************************************
//                           OpenSilexModule.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

/**
 * This interface provide entry point for OpenSILEX Modules
 * It can be implemented in every module by creating a file named
 * META-INF/services/org.opensilex.core.OpenSilexModule
 * which contains the list of implementations in this jar
 * ie: phis2ws.service.Phis2Module
 */
public interface OpenSilexModule {
    
    /**
     * This entry point allow module to initialize anything in application during initialization
     * @param app Currently initializing application
     */
    public void init(Application app);
    
}
