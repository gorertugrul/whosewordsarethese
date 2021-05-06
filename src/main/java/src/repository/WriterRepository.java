package src.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import src.model.Ratio;
import src.model.Text;
import src.model.Writer;

import java.util.List;

public interface WriterRepository extends JpaRepository<Writer, String> {

    @Query("select name from Writer")
    List<String> writers();

    @Query("select w.writings from Writer w  where w.name = ?1 ")
    List<Text> writings(String name);

    @Query("select w.wordRatio from Writer w  where w.name = ?1 ")
    List<Ratio> wordRatio(String name);

}
