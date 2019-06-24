package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.exception.EssenceNotFoundException;
import task.exception.Exception404;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class AService {
    @Autowired
    private Repository repository;

    @Autowired
    private AProcessor processor;

    public UUID createAndUpdate() throws InterruptedException, EssenceNotFoundException {
        // создание записи
        Essence essence = new Essence(LocalTime.now(), "created");
        repository.save(essence);

        //асинхронно планируем "тяжелую работу" на 2 минуты
        processor.doHeavyWork(essence);
        return essence.getId();
    }

    public Essence findById(UUID uuid) {
        return repository.findById(uuid).orElseThrow(Exception404::new);
    }
}
