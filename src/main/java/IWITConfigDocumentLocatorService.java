import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
 * @author: chris
 * @date: 01/03/2013
 */
public class IWITConfigDocumentLocatorService {

    /**
     * The file to search the classpath for,
     */
    public static final String SERVICES_FILE = "META-INF/iwit/IWIT_config.xml";


    /** @return a collection of AbstractServiceInfo provider objects found on the classpath. */
    public URL getIwitConfigURL() throws IOException {

        Enumeration<URL> providerDefinitions = this.getClass().getClassLoader().getResources(SERVICES_FILE);
        while (providerDefinitions.hasMoreElements()) {
            URL found = providerDefinitions.nextElement();
            if (providerDefinitions.hasMoreElements()) {
                String msg = String.format("Found conflicting %s documents on the classpath. I don't know which one to use", SERVICES_FILE);
                throw new RuntimeException(msg);
            }else {
                return found;
            }
        }
        throw new RuntimeException(String.format("Cannot locate %s on the classpath", SERVICES_FILE));
    }
}
