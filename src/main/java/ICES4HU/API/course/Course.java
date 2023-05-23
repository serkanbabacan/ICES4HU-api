package ICES4HU.API.course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Course {

    private @Id @GeneratedValue Long id;
    String course_name;
    String semester;
    // One to One Rel.
    // Instructor instructor
}
