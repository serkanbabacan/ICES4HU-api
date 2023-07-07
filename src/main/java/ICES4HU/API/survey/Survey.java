package ICES4HU.API.survey;

import ICES4HU.API.course.Course;
import ICES4HU.API.question.Question;
import ICES4HU.API.semester.Semester;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@Entity
public class Survey {

    private @Id @GeneratedValue Long id;

    @ManyToOne
    private Course course;

    private String description;
    private String title;

    @ManyToOne
    private Semester semester;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions =  new ArrayList<>();
}
