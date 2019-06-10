package task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import task.exception.EssenceNotFoundException;
import task.exception.Exception400;
import task.exception.Exception404;

import java.util.UUID;

@RestController
@RequestMapping("/task")
public class AController {

    private static Logger log = LoggerFactory.getLogger(AController.class);

    @Autowired
    private Repository repository;
    @Autowired
    private AService service;

    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String post() throws InterruptedException, EssenceNotFoundException {

        log.info("Start Post.");
        UUID id = service.createAndUpdate(repository);
        log.info("End Post.");

        return id.toString();
    }

    // получить запись по id
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String get(@PathVariable(value = "id") UUID id) {

        log.info("Start Get.");
        Essence essence;

        // если передан не GUID
        if (id instanceof UUID) {
            // если задачи нет
            essence = repository.findById(id).orElseThrow(Exception404::new);
            log.info("End Get.");
            return essence.getStatus();
        } else {
            log.info("Throw Exception.");
            throw new Exception400();
        }

    }
}
