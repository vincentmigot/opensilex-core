/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package opensilex.core;

import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.files.FilesConfigurationSource;
import org.cfg4j.source.reload.ReloadStrategy;
import org.cfg4j.source.reload.strategy.PeriodicalReloadStrategy;
import io.swagger.jaxrs.config.BeanConfig;
import java.nio.file.Paths;
import java.util.Arrays;
import javax.ws.rs.ApplicationPath;
import org.cfg4j.source.context.environment.Environment;
import org.glassfish.jersey.server.ResourceConfig;

/**
 *
 * @author vincent
 */
@ApplicationPath("/rest")
public class Application extends ResourceConfig {

    public interface ApplicationConfig {

        Boolean debug();

        String host();
        
        Integer port();

        String basePath();
        
        List<String> initPackages();
                
        List<String> servicePackages();
    }

    private ServletContext context;

    public Application(@Context ServletContext context) {
        this.context = context;
        initConfig();
        initPackages();
        initSwagger();
    }

    private ApplicationConfig config;

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

        // Reload configuration every 5 seconds
        ReloadStrategy reloadStrategy = new PeriodicalReloadStrategy(5, TimeUnit.SECONDS);

        ConfigurationProvider configProvider = new ConfigurationProviderBuilder()
                .withConfigurationSource(source)
                .withReloadStrategy(reloadStrategy)
                .withEnvironment(environment)
                .build();

        config = configProvider.bind("opensilex-core", ApplicationConfig.class);
    }

    private void initPackages() {
        ArrayList<String> basePackages = new ArrayList<>();
        
        basePackages.add("io.swagger.jaxrs.listing");
        
        basePackages.addAll(config.initPackages());
        
        basePackages.addAll(config.servicePackages());
        
        String packageList = String.join(";", basePackages);

        packages(packageList);
    }
    
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
}
