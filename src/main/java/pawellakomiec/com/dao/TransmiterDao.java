package pawellakomiec.com.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pawellakomiec.com.domain.Transmiter;

import java.util.List;

@Repository
public interface TransmiterDao extends CrudRepository<Transmiter, Integer> {

    Transmiter findByCode(Integer code);
    Transmiter findById(int id);
    void deleteById(int id);
    List<Transmiter> findTransmiterByDescriptionIsContaining(String value);
    void deleteAll();
}
