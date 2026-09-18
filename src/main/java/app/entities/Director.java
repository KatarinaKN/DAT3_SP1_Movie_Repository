package app.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="directors")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class Director implements IEntity {
    @Id
    private int id;
    private String name;

    @Override
    public int getID() {
        return this.id;
    }
}
