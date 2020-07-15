package interview.hibernate.exception;

import com.fasterxml.jackson.annotation.JsonProperty;
import interview.hibernate.models.Employee;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class NoEntity{
    @JsonProperty("id")
    private String id;
    @JsonProperty("entityName")
    private String entityName;
    @JsonProperty("message")
    private String message;
    public NoEntity(String id, String entityName, String message) {
        this.id = id;
        this.entityName = entityName;
        this.message = message;
    }


}
