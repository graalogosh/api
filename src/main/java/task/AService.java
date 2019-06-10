package task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import task.exception.EssenceNotFoundException;

import java.time.LocalTime;
import java.util.UUID;

@Service
public class AService {

    /**
     * POST /task
     * @param repository
     * @throws InterruptedException
     * @throws EssenceNotFoundException
     */
    @Async("asyncExecutor")
    public UUID createAndUpdate(Repository repository) throws InterruptedException, EssenceNotFoundException {

        // создание записи
        Essence essence = new Essence(LocalTime.now(), "created");
        repository.save(essence);

        // обновление записи
        Essence updateEssence = repository.findById(essence.getId())
                .orElseThrow(() -> new EssenceNotFoundException(essence.getId()));
        updateEssence.setTimestamp(LocalTime.now());
        updateEssence.setStatus("running");
        repository.save(updateEssence);

        // ожидание
        Thread.sleep(120000L);
        updateEssence = repository.findById(essence.getId())
                .orElseThrow(() -> new EssenceNotFoundException(essence.getId()));
        updateEssence.setTimestamp(LocalTime.now());
        updateEssence.setStatus("finished");
        repository.save(updateEssence);

        return essence.getId();
    }
}
