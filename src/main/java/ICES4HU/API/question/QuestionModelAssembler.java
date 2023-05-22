package ICES4HU.API.question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class QuestionModelAssembler implements RepresentationModelAssembler<Question, EntityModel<Question>> {
    @Override
    public EntityModel<Question> toModel(Question question) {

        return EntityModel.of(question, //
                linkTo(methodOn(QuestionController.class).one(question.getId())).withSelfRel(),
                linkTo(methodOn(QuestionController.class).all()).withRel("question"));
    }
}
