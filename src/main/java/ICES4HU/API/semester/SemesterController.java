package ICES4HU.API.semester;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SemesterController {
    private final SemesterRepository repository;
    private final SemesterModelAssembler assembler;

    SemesterController(SemesterRepository repository, SemesterModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/semesters")
    ResponseEntity<?> all() {
        return ResponseEntity.ok(
                assembler.toCollectionModel(repository.findAll())
        );
    }

    @GetMapping("/semester/{id}")
    public ResponseEntity<?> one(@PathVariable Long id) {
        return repository.findById(id)
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/semester/currentSemester")
    public ResponseEntity<?> getCurrentSemester() {
        return repository.findTopByOrderByIdDesc()
                .map(assembler::toModel)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/semester")
    ResponseEntity<?> newUser(@RequestBody Semester semester){
        EntityModel<Semester> entityModel = assembler.toModel(repository.save(semester));
        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping("/semester/{id}")
    ResponseEntity<?> deleteUser(@PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
