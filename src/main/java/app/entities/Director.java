package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="directors")
@Getter
@NoArgsConstructor
@ToString
public class Director implements IEntity {
    @Id
    private Long id;

    private String name;

    @Override
    public Long getID() {
        return this.id;
    }
}
