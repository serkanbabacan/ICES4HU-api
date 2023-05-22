package ICES4HU.API.question;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class QuestionController {
    
    private final QuestionRepository repository;
    private final QuestionModelAssembler assembler;

    public QuestionController(QuestionRepository repository, QuestionModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // Aggregate root

    @GetMapping("/questions")
    public ResponseEntity<?> all() {
    
        List<EntityModel<Question>> questions = repository.findAll().stream()
            .map(assembler::toModel)
            .collect(Collectors.toList());

        return ResponseEntity.ok(
                CollectionModel.of(questions, linkTo(methodOn(QuestionController.class).all()).withSelfRel())
        );
    }

    @GetMapping("/questions/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/questions/{id}")
    public ResponseEntity<?> deleteQuestion(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/surveys/{id}/questions")
    public ResponseEntity<?> findQuestions(@PathVariable Long id){
        List<EntityModel<Question>> questions = repository.findBySurveyId(id).stream()
                .map(assembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(questions, linkTo(methodOn(QuestionController.class).all()).withSelfRel())
        );
    }

    @PostMapping("/surveys/{id}/questions")
    public ResponseEntity<?> AddQuestions(@RequestBody Question newQuestion, @PathVariable Long id){
        List<EntityModel<Question>> questions = repository.findBySurveyId(id).stream()
                .map(assembler::toModel)
                .toList();

        return ResponseEntity.ok(
                CollectionModel.of(questions, linkTo(methodOn(QuestionController.class).all()).withSelfRel())
        );
    }


}
