package th.mfu;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HelloControllerTest {

    @Autowired
    private HelloController controller;

    @Test
    public void testHello() {

        // Act
        String response = controller.hello();
        // Assert
        assertEquals("Hello World!", response);
    }

    @Test
    public void testHi() {
        // Act
        String response = controller.hi(" CMU");
        // Assert
        assertEquals("Hello CMU", response);
    }

    @Test
    public void testSum() {
        int response = controller.sum(5, 6);
        assertEquals(11, response);
        int response1 = controller.sum(4, 2);
        assertEquals(6, response1);
        int response2 = controller.sum(3, 4);
        assertEquals(7, response2);
    }

}
