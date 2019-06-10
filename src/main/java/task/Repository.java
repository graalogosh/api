package task;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface Repository extends CrudRepository<Essence, UUID> {}
