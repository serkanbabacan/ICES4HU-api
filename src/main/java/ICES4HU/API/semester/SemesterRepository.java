package ICES4HU.API.semester;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SemesterRepository extends JpaRepository<Semester,Long> {
    Optional<Semester> findTopByOrderByIdDesc();
}
