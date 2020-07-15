package interview.hibernate.exception;

import javax.persistence.Entity;

public class NoEmployeeException extends RuntimeException {
    private String id;
    public NoEmployeeException(String message, String id) {
        super(message);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
