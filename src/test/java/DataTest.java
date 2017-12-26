import Data.Data;
import org.junit.Test;

import static org.junit.Assert.*;

public class DataTest {
    @Test
    public void datatest() {
        assertEquals(0, Data.f_ClassFlag);
    }

    @Test
    public void datatest1() {
        assertEquals("class_name", Data.test_key);
    }
}
