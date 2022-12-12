package app.prog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "book")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private Integer pageNumber;
    private LocalDate releaseDate;
    private Integer author_id;
    @ManyToOne
    @JoinColumn(name = "author_id", insertable = false, updatable = false, nullable = true)
    private AuthorEntity author;

    @ManyToMany(mappedBy = "books")
    private List<CategoryEntity> categories = new ArrayList<>();

    public boolean hasAuthor() {
        return author != null;
    }

}
