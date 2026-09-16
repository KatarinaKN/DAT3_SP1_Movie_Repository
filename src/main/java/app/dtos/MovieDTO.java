package app.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class MovieDTO {
    private Long id;
    private String title;
    private String overview;
    private String release_date;

    @JsonProperty("vote_average")
    private double averageRating;
    private List<DirectorDTO> director;
    private List<ActorDTO> actor;
    private List<GenreDTO> genres;


}


