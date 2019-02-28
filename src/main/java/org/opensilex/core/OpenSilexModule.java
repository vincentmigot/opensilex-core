//******************************************************************************
//                           OpenSilexModule.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

import java.util.ArrayList;
import java.util.List;

/**
 * This abstract class provide entry point for OpenSILEX modules inside application
 * Simply extends this class to create a module.
 */
public abstract class OpenSilexModule {

    protected  OpenSilexResourceConfig app = null;
    
    /**
     * This setter allow to give a reference to the main application to each module
     * 
     * @param app the main application
     */
    final public void setApplication(OpenSilexResourceConfig app) {
        this.app = app;
    }

    /**
     * This method is called during application initialization to get all packages
     * to scan for components like request filters
     * 
     * @return List of packages to scan
     */
    public List<String> getPackagesToScan() {
        return new ArrayList<String>();
    }

    /**
     * This method is called during application initialization to get all packages
     * to scan for jersey web services wich will be displayed into swagger UI
     * 
     * @return List of packages to scan for web services
     */
    public List<String> getServicesPackagesToScan() {
        return new ArrayList<String>();
    }
    
    /**
     * This entry point allow module to initialize anything in application
     * after all configuration is loaded at the end of application loading
     */
    public void init() {
        // Do nothing by default; 
    }
}
