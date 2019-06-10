package task;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalTime;
import java.util.UUID;

@Entity
public class Essence {

    /*@Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;*/

    @Id
    private UUID id;
    private LocalTime timestamp;
    private String status;

    protected Essence() {}

    public Essence(LocalTime timestamp, String status) {
        this.id = UUID.randomUUID();
        this.timestamp = timestamp;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
