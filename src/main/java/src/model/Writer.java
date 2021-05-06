package src.model;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Entity
@Table(name = "WRITER_TABLE")
public class Writer {
    @Id
    @Column(name = "NAME")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<String, Text> writings = new HashMap<>();

    @ManyToMany(cascade = CascadeType.ALL)
    private Map<String, Ratio> wordRatio = new HashMap<>();


    public Writer() {
    }

    public Writer(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<String, Text> getWritings() {
        return writings;
    }

    public void setWritings(Map<String, Text> writings) {
        this.writings = writings;
    }

    public void addWritings(Text text) {
        this.writings.put(text.getSubject(), text);
    }

    public Map<String, Ratio> getWordRatio() {
        return wordRatio;
    }

    public void setWordRatio(Map<String, Ratio> wordRatio) {
        this.wordRatio = wordRatio;
    }

    public void updateRatio(Map<String, Ratio> wordRatio) {
        wordRatio.forEach((word, ratio) -> this.wordRatio.merge(word, ratio, Ratio::combine));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(name, writer.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
