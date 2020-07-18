package interview.spring.websecurity.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="department")
@Data
@NoArgsConstructor
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id_;
    private String deptName;
//    @JsonIgnore
    @OneToMany(
            mappedBy = "department",
            cascade = CascadeType.ALL
            //,fetch = FetchType.LAZY // will not load employees
            ,fetch = FetchType.EAGER
    )
    private List<Employee> employees = new ArrayList<>();
}
