//******************************************************************************
//                      ApplicationCoreConfig.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core.config;

/**
 * Core configuration interface
 */
public interface ApplicationCoreConfig {

    /**
     * Flag to determine if application is in debug mode or not
     *
     * @return true Application in debug mode false Application in production
     * mode
     */
    Boolean debug();

    /**
     * Url scheme http or https
     *
     * @return Url scheme
     */
    String scheme();
    
    /**
     * Web server host name or IP address
     *
     * @return Hostname or IP address
     */
    String host();

    /**
     * Web server port
     *
     * @return Port
     */
    Integer port();

    /**
     * Web service base application path
     *
     * @return Base application path
     */
    String basePath();

    /**
     * Default application language
     * 
     * @return default application language
     */
    String defaultLanguage();

}
