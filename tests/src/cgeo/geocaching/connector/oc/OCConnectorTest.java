package cgeo.geocaching.connector.oc;

import static org.assertj.core.api.Assertions.assertThat;

import cgeo.geocaching.connector.ConnectorFactory;
import cgeo.geocaching.connector.IConnector;

import junit.framework.TestCase;

public class OCConnectorTest extends TestCase {

    /**
     * OC.DE used up the 4 digit/character name space and switched over to 5 recently
     */
    public static void testCanHandleNew5DigitCodes() {
        final IConnector ocConnector = getOcDeConnector();
        assertThat(ocConnector.canHandle("OCFFFF")).isTrue();
        assertThat(ocConnector.canHandle("OC10000")).isTrue();
    }

    private static IConnector getOcDeConnector() {
        final IConnector ocConnector = ConnectorFactory.getConnector("OCXXX");
        assertThat(ocConnector.getHost().contains(".de")).isTrue();
        return ocConnector;
    }

    public static void testGetGeocodeFromUrlDe() throws Exception {
        final IConnector connector = ConnectorFactory.getConnector("OC0028");
        assertThat(connector.getGeocodeFromUrl("http://opencaching.de/OC0028")).isEqualTo("OC0028");
        assertThat(connector.getGeocodeFromUrl("http://www.opencaching.de/OC0028")).isEqualTo("OC0028");
    }

    public static void testGetGeocodeFromUrlUs() throws Exception {
        final IConnector connector = ConnectorFactory.getConnector("OU07A0");
        assertThat(connector.getGeocodeFromUrl("http://www.opencaching.us/viewcache.php?wp=OU07A0")).isEqualTo("OU07A0");
    }

}
