package ICES4HU.API.course;

import ICES4HU.API.instructor.Instructor;
import ICES4HU.API.semester.Semester;
import ICES4HU.API.survey.Survey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    private @Id @GeneratedValue Long id;
    private String course_name;

    @ManyToOne
    private Semester semester;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    @JsonIgnore
    private List<Survey> survey = new ArrayList<>();

    private String instructor;
}
