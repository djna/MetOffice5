package training.metofficeweather;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.stream.Collectors;

/*
 * Access to application configuration supplied in Java properties.
 * String values, accessed by name.
 */
public class ApplicationProperties {
    private static final Logger theLogger = LogManager.getLogger("Weather");

    private Map<String,String> propertyMap;
    private String propertyFileName;

    /*
     * Prepare properties for lookup with default.
     * Add properties from specified file to System Properties.
     */
    public ApplicationProperties(String initPropertyFileName) {
        propertyFileName = initPropertyFileName;

        Properties systemProperties = System.getProperties();
        try {
            InputStream propertyStream = this.getClass().getResourceAsStream( propertyFileName );
            if ( propertyStream != null ){
                systemProperties.load(propertyStream);
            } else {
                theLogger.warn("Failed to open properties from {}", propertyFileName);
            }
        } catch(IOException ioe){
            theLogger.warn("Failed to read properties from {}: {}", propertyFileName, ioe.getMessage());
        }

        // populate a map so that we can use getOrDefault()
        propertyMap = new HashMap<>();
        systemProperties.forEach(
                (k, v) -> propertyMap.put( k.toString(), v.toString() )
        );

        theLogger.info("Properties: \n{}",
                propertyMap.keySet().stream()
                        .map( (k) -> k + "=" + propertyMap.get(k) )
                        .collect( Collectors.joining( "\n" ))
                );
    }

    /*
     * Use default property files.
     */
    public ApplicationProperties() {
        this("/application.properties");
    }

    /*
     * return named property or default if none found
     */
    String getProperty(String name, String defaultValue){
          return propertyMap.getOrDefault(name, defaultValue );
    }
}
