package ICES4HU.API.course;

import ICES4HU.API.survey.Survey;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Course {

    private @Id @GeneratedValue Long id;
    private String course_name;
    private String semester;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval=true, mappedBy = "course")
    @JsonIgnore
    private List<Survey> survey = new ArrayList<>();
    // One to One Rel.
    // Instructor instructor
}
