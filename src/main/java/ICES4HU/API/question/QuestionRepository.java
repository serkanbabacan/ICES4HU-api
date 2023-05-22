package ICES4HU.API.question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findBySurveyId(Long id);
}