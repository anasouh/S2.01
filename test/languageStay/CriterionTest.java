package languageStay;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CriterionTest {
    Criterion c5, c6, c7;
    
    @BeforeEach
    public void testInitialization() {
        c5 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "yes");
        c6 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "peut-etre");
        c7 = new Criterion(CriterionName.HOST_HAS_ANIMAL, "no");
    }

    @Test
    public void testIsValid(){
        assertTrue(c5.isValid());
        assertTrue(c7.isValid());
        assertFalse(c6.isValid());
    }
}
