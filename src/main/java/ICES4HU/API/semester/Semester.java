package ICES4HU.API.semester;

import ICES4HU.API.course.Course;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class Semester {

    private @Id @GeneratedValue Long id;
    private String name;
    private Date start_date;
    private Date end_date;

    @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses =  new ArrayList<>();
}
