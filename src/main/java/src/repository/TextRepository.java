package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import src.model.Text;

import java.util.List;

@org.springframework.stereotype.Repository
public interface TextRepository extends JpaRepository<Text, Long> {

    @Query("select writer from Text")
    List<String> writers();

    @Query("select t.writer from Text t  where t.subject = ?1 and t.writer = ?2")
    List<String> findWriterNames(String subject, String writer);
}
