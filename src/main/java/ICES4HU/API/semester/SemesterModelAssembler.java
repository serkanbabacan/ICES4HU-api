package ICES4HU.API.semester;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class SemesterModelAssembler implements RepresentationModelAssembler<Semester, EntityModel<Semester>> {

    @Override
    public EntityModel<Semester> toModel(Semester semester) {
        return EntityModel.of(semester,
                linkTo(methodOn(SemesterController.class).one(semester.getId())).withSelfRel(),
                linkTo(methodOn(SemesterController.class).all()).withRel("semesters")
        );
    }
}
