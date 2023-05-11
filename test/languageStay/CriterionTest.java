package languageStay;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

    @Test
    public void testIsNumeric(){
        assertTrue(Criterion.isNumeric("42"));
        assertTrue(Criterion.isNumeric("042"));
        assertFalse(Criterion.isNumeric("42f"));
    }

    @Test
    public void testIsMonth(){
        assertTrue(Criterion.isMonth("11"));
        assertFalse(Criterion.isMonth("18"));
        assertTrue(Criterion.isNumeric("01"));
        assertFalse(Criterion.isMonth("11f"));
    }

    @Test
    public void testIsDay(){
        assertTrue(Criterion.isDays("30","11"));
        assertFalse(Criterion.isDays("31", "11"));
        assertTrue(Criterion.isDays("01", "11"));
        assertFalse(Criterion.isDays("F1", "11"));
    }
}
