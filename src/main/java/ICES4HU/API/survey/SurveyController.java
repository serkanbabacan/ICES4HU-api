package ICES4HU.API.survey;

import ICES4HU.API.question.Question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class SurveyController {
    
    private final SurveyRepository repository;
    private final SurveyModelAssembler assembler;
    
    SurveyController(SurveyRepository repository, SurveyModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/surveys")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(repository.findAll()));
    }

    @PostMapping("/surveys")
    public ResponseEntity<?> newSurvey(@RequestBody Survey newSurvey) {
        List<Question> questions = newSurvey.getQuestions();
        newSurvey.setQuestions(null); // Temporarily remove questions to avoid circular references

        Survey savedSurvey = repository.save(newSurvey);

        for (Question question : questions) {
            question.setSurvey(savedSurvey);
        }
        savedSurvey.setQuestions(questions);

        repository.save(savedSurvey);

        EntityModel<Survey> entityModel = assembler.toModel(savedSurvey);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @GetMapping("/surveys/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/surveys/{id}")
    public ResponseEntity<?> replaceSurvey(@RequestBody Survey newSurvey, @PathVariable Long id) {
        List<Question> questions = newSurvey.getQuestions();
        newSurvey.setQuestions(null); // Temporarily remove questions to avoid circular references

        Survey updatedSurvey = repository.findById(id)
                .map(survey -> {
                    survey.setCourse_id(newSurvey.getCourse_id());
                    survey.setStudent_id(newSurvey.getStudent_id());
                    survey.setSurvey_name(newSurvey.getSurvey_name());
                    survey.setSurvey_date(newSurvey.getSurvey_date());
                    survey.setSurvey_text(newSurvey.getSurvey_text());
                    survey.setSurvey_title(newSurvey.getSurvey_title());
                    return repository.save(survey);
                })
                .orElseGet(() -> {
                    newSurvey.setId(id);
                    return repository.save(newSurvey);
                });

        for (Question question : questions) {
            question.setSurvey(updatedSurvey);
        }
        updatedSurvey.setQuestions(questions);

        repository.save(updatedSurvey);

        EntityModel<Survey> entityModel = assembler.toModel(updatedSurvey);
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/surveys/{id}")
    ResponseEntity<?> deleteSurvey(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
