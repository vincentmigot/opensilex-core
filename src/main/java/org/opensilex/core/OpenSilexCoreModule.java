//******************************************************************************
//                             OpenSilexCoreModule.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Core OpenSILEX module implementation
 */
public class OpenSilexCoreModule extends OpenSilexModule {

    @Override
    public List<String> getPackagesToScan() {
        return Arrays.asList(new String[]{
            // Package for swagger UI service listing
            "io.swagger.jaxrs.listing"
        });
    }

    @Override
    public List<String> getServicesPackagesToScan() {
        List<String> packages = new ArrayList<>();

        if (app.isDev()) {
            // If application is in development mode load sample services package
            packages.add("org.opensilex.core.api.sample");
        }

        return packages;
    }
}
