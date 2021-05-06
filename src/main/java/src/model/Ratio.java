package src.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "RATIO_TABLE")
public class Ratio {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private long id;
    @Column(name = "TEXT")
    private String text;
    @Column(name = "COUNT")
    private int count;

    public Ratio() {
    }

    public Ratio(String text, int count) {
        this.text = text;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Ratio combine(Ratio ratio) {
        this.count = this.count + ratio.count;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ratio ratio = (Ratio) o;
        return id == ratio.id && count == ratio.count && Objects.equals(text, ratio.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text, count);
    }
}
