package ICES4HU.API.question;

import ICES4HU.API.survey.Survey;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@Entity
public class Question {
    
    private @Id @GeneratedValue Long id;
    private String question;

    //use JsonIgnore if you don't need to return the survey object
    //Question controller api called as /survey/id/questions, so no need to know the survey (?)
    @ManyToOne
    private Survey survey;

    @ElementCollection
    private List<String> answers;

}
