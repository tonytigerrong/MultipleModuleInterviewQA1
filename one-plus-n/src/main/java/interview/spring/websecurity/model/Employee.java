package interview.spring.websecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "employee")
@Data
@Getter
@Setter
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String password;

//    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="department_id")
    private Department department;
}
