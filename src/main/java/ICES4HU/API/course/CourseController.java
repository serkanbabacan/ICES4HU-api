package ICES4HU.API.course;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    private final CourseRepository repository;
    private final CourseModelAssembler assembler;

    CourseController(CourseRepository repository,CourseModelAssembler assembler){
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/courses")
    ResponseEntity<?> all(){
        return ResponseEntity.ok(
                assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping("/courses")
    ResponseEntity<?> newCourse(@RequestBody Course course){
        EntityModel<Course> entityModel = assembler.toModel(repository.save(course));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/courses/{id}")
    ResponseEntity<?> one(@PathVariable Long id){
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/courses/{id}")
    ResponseEntity<?> replaceCourse(@RequestBody Course newCourse, @PathVariable Long id) {
        Course updatedCourse = repository.findById(id)
                .map(course -> {
                    course.setCourse_name(newCourse.getCourse_name());
                    course.setSemester(newCourse.getSemester());
                    course.setSurvey(newCourse.getSurvey());
                    course.setInstructor(newCourse.getInstructor());
                    return repository.save(course);
                })
                .orElseGet(() -> {
                    newCourse.setId(id);
                    return repository.save(newCourse);
                });

        EntityModel<Course> entityModel = assembler.toModel(updatedCourse);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/courses/{id}")
    ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
