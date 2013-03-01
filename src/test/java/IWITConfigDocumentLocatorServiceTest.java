import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * @author: chris
 * @date: 01/03/2013
 */
public class IWITConfigDocumentLocatorServiceTest {

    private IWITConfigDocumentLocatorService unit;

    @Before
    public void before() {
        this.unit = new IWITConfigDocumentLocatorService();
    }

    @Test
    public void shouldLoadConfigurationFileFromClasspath() throws Exception {
        URL expected = getExpectedIWitConfigResourceStream().getURL();
        Assert.assertEquals(getContent(expected), getContent(unit.getIwitConfigURL()));
    }

    public Resource getExpectedIWitConfigResourceStream() {
        return new ClassPathResource("expected-iwit-config.xml");
    }

    public static String getContent(URL in) throws Exception {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(in.openStream()));

        StringBuilder response = new StringBuilder();
        String inputLine = null;

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        return response.toString();
    }
}
