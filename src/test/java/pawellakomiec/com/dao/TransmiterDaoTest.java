package pawellakomiec.com.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pawellakomiec.com.domain.Transmiter;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

@Rollback
@Commit
@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class TransmiterDaoTest {

    @Autowired
    TransmiterDao transmiterDao;

    @Test
    public void addNewTransmiter() {

        Random generator = new Random();
        Integer randomCode = generator.nextInt(100000) + 1;

        Transmiter transmiter = new Transmiter("Transmiter1", "B8", randomCode, "Transmiter description");
        transmiterDao.save(transmiter);

        assertEquals(transmiter.getName(), transmiterDao.findByCode(randomCode).getName());
    }

    @Test
    public void updateTransmiter() {

        Random generator = new Random();
        Integer randomCode = generator.nextInt(100000) + 1;

        Transmiter transmiter = new Transmiter("Transmiter1", "B8", randomCode, "Transmiter description");
        transmiterDao.save(transmiter);

        Transmiter addedTransmiterToDb = transmiterDao.findByCode(randomCode);

        addedTransmiterToDb.setName("scodea");
        transmiterDao.save(addedTransmiterToDb);

        assertEquals(addedTransmiterToDb, transmiterDao.findById(addedTransmiterToDb.getId()));
    }

    @Test
    public void checkDelete() {

        Random generator = new Random();
        Integer randomCode = generator.nextInt(100000) + 1;

        Transmiter transmiter = new Transmiter("Transmiter1", "B8", randomCode, "Transmiter description");
        transmiterDao.save(transmiter);

        Transmiter addedTransmiterToDb = transmiterDao.findByCode(randomCode);
        transmiterDao.deleteById(addedTransmiterToDb.getId());

        assertNull(transmiterDao.findById(addedTransmiterToDb.getId()));
    }

    @Test
    public void checkFindByText() {

        for (int i = 0; i < 10; i++) {
            Random generator = new Random();
            Integer randomCode = generator.nextInt(100000) + 1;

            Transmiter transmiter = new Transmiter("Transmiter1", "SONY", randomCode, "Transmiter description");
            transmiterDao.save(transmiter);
        }

        List<Transmiter> transmiterList = transmiterDao.findTransmiterByDescriptionIsContaining("Transmiter description");
        assertTrue(transmiterList.size() > 10);
    }
}