package pawellakomiec.com.services;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import pawellakomiec.com.domain.Transmiter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
public class TransmiterDBunitTest {

    @Autowired
    private TransmiterService transmiterService;

    @Test
    @DatabaseSetup("ds-0.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-1.xml")
    public void testCreateRead() {

        Transmiter transmiter = new Transmiter("Transmiter1", "SONY", 2221, "Transmiter description");
        transmiter.setId(1);
        transmiterService.saveTransmiter(transmiter);
        assertEquals(transmiterService.findById(1).getName(), "Transmiter1");
    }


    @Test
    @DatabaseSetup("ds-0.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-2.xml")
    public void testUpdate() {

        Transmiter transmiter = new Transmiter("Transmiter1", "SONY", 2221, "Transmiter description");
        transmiter.setId(1);
        transmiterService.saveTransmiter(transmiter);

        Transmiter foundByIdTransmiter = transmiterService.findById(1);
        foundByIdTransmiter.setName("SAMSUNG");
        transmiterService.saveTransmiter(foundByIdTransmiter);

        assertEquals(transmiterService.findById(1).getName(), "SAMSUNG");
    }

    @Test
    @DatabaseSetup("ds-3.xml")
    @ExpectedDatabase(assertionMode = DatabaseAssertionMode.NON_STRICT, value = "ds-4.xml")
    public void deleteTest() {

        for (int i = 1; i <= 10; i++) {
            Transmiter transmiter = new Transmiter("SAMSUNG", "SONY", 2220 + i, "Transmiter description");
            transmiter.setId(i);
            transmiterService.saveTransmiter(transmiter);
        }

        assertEquals(10, transmiterService.getAllItems().size());

        transmiterService.deleteById(1);
        transmiterService.deleteById(2);
        transmiterService.deleteById(3);
        transmiterService.deleteById(4);
        transmiterService.deleteById(5);

        assertEquals(5, transmiterService.getAllItems().size());
        assertEquals("SAMSUNG", transmiterService.findById(6).getName());
    }
}
