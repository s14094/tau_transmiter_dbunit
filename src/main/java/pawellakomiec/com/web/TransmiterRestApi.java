package pawellakomiec.com.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import pawellakomiec.com.domain.Transmiter;
import pawellakomiec.com.services.TransmiterService;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RestController
public class TransmiterRestApi {

    @Autowired
    private TransmiterService transmiterService;

    @GetMapping(value = "/all-transmiters")
    public List<Transmiter> getAllTransmiters() {
        return (List<Transmiter>) transmiterService.getAllItems();
    }

    @GetMapping(value = "/generate")
    public List<Transmiter> generateDate() {
        transmiterService.generateSampleData();
        return (List<Transmiter>) transmiterService.getAllItems();
    }

    @GetMapping(value = "/get-by-id/{id}")
    public Transmiter getTransmiterById(@PathVariable("id") int id) {
        return transmiterService.findById(id);
    }

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transmiter addNewTransmiter(@RequestBody Transmiter transmiter) {
        return transmiterService.saveTransmiter(transmiter);
    }

    @PostMapping(value = "/update/{id}/{name}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Transmiter updateNameById(@PathVariable("id") int id, @PathVariable("name") String name) {
        Transmiter transmiter = transmiterService.findById(id);
        transmiter.setName(name);
        return transmiterService.saveTransmiter(transmiter);
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteById(@PathVariable("id") int id) {
        transmiterService.deleteById(id);
        return (transmiterService.findById(id) == null) ? "accept delete" : "unable to delete transmister";
    }

    @GetMapping(value = "/delete-all")
    public String deleteAll() {
        transmiterService.deleteAll();
        return (transmiterService.getAllItems().size() == 0) ? "all transmiters deleted" : "unable to delete transmister";
    }
}
