import Data.Data;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static org.junit.Assert.*;

public class ReaderTest {
    File configFile = new File("config.properties");
    FileReader reader = new FileReader(configFile);
    Properties props = new Properties();

    public ReaderTest() throws IOException {
        props.load(reader);
    }

    @Test
    public void readertest() {
        assertNotNull(props.getProperty("1"));
    }

    @Test
    public void readertest1() {
        assertNotNull(props.getProperty("num_of_productions"));
    }

    @Test
    public void readertest2() {
        assertNull(props.getProperty("0"));
    }
}
