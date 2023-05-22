package ICES4HU.API.survey;

import org.springframework.data.jpa.repository.JpaRepository;

interface SurveyRepository extends JpaRepository<Survey, Long> {
}
