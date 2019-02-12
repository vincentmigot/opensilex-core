//******************************************************************************
//                             Application.java
// OpenSILEX
// Copyright © INRA 2019
// Creation date: 01 jan. 2019
// Contact: vincent.migot@inra.fr, anne.tireau@inra.fr, pascal.neveu@inra.fr
//******************************************************************************
package org.opensilex.core;

import org.opensilex.core.config.ApplicationCoreConfig;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.files.FilesConfigurationSource;
import io.swagger.jaxrs.config.BeanConfig;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.ServiceLoader;
import javax.inject.Singleton;
import javax.ws.rs.ApplicationPath;
import org.cfg4j.source.context.environment.Environment;
import org.glassfish.jersey.server.ResourceConfig;
import org.opensilex.core.config.MongoDBConfig;
import org.opensilex.core.config.RDF4JConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class is the main entry point of OpenSILEX application It initialize
 * everything into its constructor
 */
@ApplicationPath("/rest")
@Singleton
public class Application extends ResourceConfig {

    final private static Logger LOGGER = LoggerFactory.getLogger(Application.class);

    final private ServletContext context;

    /**
     * Application configuration
     */
    private ApplicationCoreConfig config;

    /**
     * Constructor where Servlet context is automatically injected
     *
     * @param context Injected Servlet context
     */
    public Application(@Context ServletContext context) {
        this.context = context;

        initConfig();
        initPackages();
        initSwagger();
        initModules();
    }

    /**
     * Parse YAML configuration and initialize "config" class member
     */
    private void initConfig() {
        // Specify which files to load. Configuration from all files will be merged.
        ConfigFilesProvider configFilesProvider = () -> Arrays.asList(
                Paths.get("opensilex.yaml")
        );

        // Use local files as configuration store
        ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);

        // Use relative paths
        String realPath = context.getRealPath("/WEB-INF/classes/config/");
        Environment environment = new ImmutableEnvironment(realPath);

        ConfigurationProvider configProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withEnvironment(environment)
                .build();

        config = configProvider.bind("opensilex-core", ApplicationCoreConfig.class);

        RDF4JConfig configRDF4J = configProvider.bind("opensilex-core-rdf4j", RDF4JConfig.class);
        MongoDBConfig configMongoDB = configProvider.bind("opensilex-core-mongo", MongoDBConfig.class);

        register(new ApplicationServiceBinder(
                config,
                configRDF4J,
                configMongoDB
        ));
    }

    /**
     * Initialize packages list to scan for services
     */
    private void initPackages() {
        ArrayList<String> basePackages = new ArrayList<>();

        basePackages.add("io.swagger.jaxrs.listing");

        basePackages.addAll(config.initPackages());

        basePackages.addAll(config.servicePackages());

        String packageList = String.join(";", basePackages);

        packages(packageList);
    }

    /**
     * Initialize swagger UI
     */
    private void initSwagger() {
        String packageList = String.join(",", config.servicePackages());

        //Swagger
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setVersion("1.0.2");
        beanConfig.setSchemes(new String[]{"http"});

        beanConfig.setHost(config.host() + ":" + config.port());
        beanConfig.setBasePath(config.basePath());

        beanConfig.setResourcePackage(packageList);
        beanConfig.setScan(true);
    }

    /**
     * Initialize OpenSILEX modules declared in external jar packages
     */
    private void initModules() {
        ServiceLoader<OpenSilexModule> loader = ServiceLoader.load(OpenSilexModule.class);

        for (OpenSilexModule module : loader) {
            module.init(this);
        }
    }

}
