import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class CriterionTest {
    Criterion c1, c2;
    
    @BeforeEach
    public void testInitialization() {
        
    }

    @Test
    public void testIsIn() {

        c1 = new Criterion(CriterionName.HOST_FOOD, "vegetarian, fff");
        c2 = new Criterion(CriterionName.GUEST_FOOD, "vegetarian, nonuts");

        assertTrue(c1.allIn(c2.getValue()));


    }
}
