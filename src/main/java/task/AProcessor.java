package task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import task.exception.EssenceNotFoundException;

import java.time.LocalTime;
import java.util.function.Supplier;

@Service
public class AProcessor {
    @Autowired
    private Repository repository;

    @Async("asyncExecutor")
    public void doHeavyWork(final Essence essence) throws EssenceNotFoundException, InterruptedException {
        // обновление записи
        Supplier<EssenceNotFoundException> essenceNotFoundExceptionSupplier = () -> new EssenceNotFoundException(essence.getId());
        Essence updateEssence = repository.findById(essence.getId())
                .orElseThrow(essenceNotFoundExceptionSupplier);
        updateEssence.setTimestamp(LocalTime.now());
        updateEssence.setStatus("running");
        repository.save(updateEssence);

        // ожидание
        Thread.sleep(120000L);
        updateEssence = repository.findById(essence.getId())
                .orElseThrow(essenceNotFoundExceptionSupplier);
        updateEssence.setTimestamp(LocalTime.now());
        updateEssence.setStatus("finished");
        repository.save(updateEssence);
    }
}
