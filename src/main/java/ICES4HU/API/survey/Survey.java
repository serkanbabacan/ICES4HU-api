package ICES4HU.API.survey;

import ICES4HU.API.question.Question;
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

    //Change this when course implemented
    //private Course course;
    private int course_id;
    private int student_id;
    private String survey_name;
    private Date survey_date;
    private String survey_text;
    private String survey_title;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions =  new ArrayList<>();
}
