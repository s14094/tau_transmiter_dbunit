package pawellakomiec.com.services;

import org.springframework.stereotype.Service;
import pawellakomiec.com.dao.TransmiterDao;
import pawellakomiec.com.domain.Transmiter;

import java.util.List;
import java.util.UUID;

@Service
public class TransmiterService {

    private TransmiterDao transmiterDao;

    public TransmiterService(TransmiterDao transmiterDao) {
        this.transmiterDao = transmiterDao;
    }

    public Transmiter findById(int id) {
        return transmiterDao.findById(id);
    }

    public Transmiter findByCode(Integer code) {
        return transmiterDao.findByCode(code);
    }

    public Transmiter saveTransmiter(Transmiter transmiter) {
        return transmiterDao.save(transmiter);
    }

    public List<Transmiter> getAllItems() {
        return (List<Transmiter>) transmiterDao.findAll();
    }

    public void deleteById(int id) {
        transmiterDao.deleteById(id);
    }

    public void generateSampleData() {
        for (int i = 1; i <= 20; i++) {
            Transmiter transmiter = new Transmiter("Transmiter1", "SONY", 3320 + i, "Transmiter description sole " + UUID.randomUUID());
            transmiter.setId(i);
            transmiterDao.save(transmiter);
        }
    }

    public void deleteAll() {
        transmiterDao.deleteAll();
    }
}

