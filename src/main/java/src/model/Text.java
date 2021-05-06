package src.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "TEXT_TABLE")
public class Text implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "ID")
    private long id;
    @Column(name = "WRITER")
    private String writer;
    @Column(name = "SUBJECT")
    private String subject;

    @Column(name = "CONTENT")
    @Size(max = 1337)
    private String content;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Text text = (Text) o;
        return id == text.id && Objects.equals(writer, text.writer) && Objects.equals(subject, text.subject) && Objects.equals(content, text.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, subject, content);
    }

    @Override
    public String toString() {
        return "Text{" +
                "writer='" + writer + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
