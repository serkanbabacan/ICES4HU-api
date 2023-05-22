package ICES4HU.API.survey;

import ICES4HU.API.question.QuestionController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
class SurveyModelAssembler implements RepresentationModelAssembler<Survey, EntityModel<Survey>> {
    @Override
    public EntityModel<Survey> toModel(Survey employee) {

        return EntityModel.of(employee, //
                linkTo(methodOn(SurveyController.class).one(employee.getId())).withSelfRel(),
                linkTo(methodOn(SurveyController.class).all()).withRel("surveys"),
                linkTo(methodOn(QuestionController.class).findQuestions(employee.getId() )).withRel("questions"));
    }
}

