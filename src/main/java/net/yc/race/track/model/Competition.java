package net.yc.race.track.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "competitions")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Course name cannot be blank.")
    @Size(max = 100, message = "Course name cannot exceed 100 characters.")
    @Column(nullable = false)
    private String courseName;


    @NotBlank(message = "Coordinates GPS cannot be blank.")
    @Pattern(
            regexp = "^[-+]?\\d{1,2}\\.\\d+,[-+]?\\d{1,3}\\.\\d+$",
            message = "Coordinates GPS must be in the format 'latitude,longitude'."
    )
    @Column(nullable = false)
    private String coordinatesGPS;

    @NotBlank(message = "Distance cannot be null.")
    @Positive(message = "Distance must be a positive number.")
    @Column(name = "distance", nullable = false)
    private Long distance;

    @NotBlank(message = "Start date and time cannot be null.")
    @Future(message = "Start date and time must be in the future.")
    @Column(name = "creation_date", nullable = false)
    private Date startDateTime;

    @NotBlank(message = "Delay duration cannot be null.")
    @Column(name = "delay", nullable = false)
    private Date delayDuration;

    @ManyToOne
    @NotNull(message = "Season ID is required.")
    @JoinColumn(name = "season_id", nullable = false)
    private Season season;

    @OneToMany(mappedBy = "competition", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Result> results;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "competition", fetch = FetchType.LAZY)
    @Size(min = 1, message = "At least one pigeon must be provided.")
    private List<Pigeon> pigeons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCoordinatesGPS() {
        return coordinatesGPS;
    }

    public void setCoordinatesGPS(String coordinatesGPS) {
        this.coordinatesGPS = coordinatesGPS;
    }

    public Long getDistance() {
        return distance;
    }

    public void setDistance(Long distance) {
        this.distance = distance;
    }

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getDelayDuration() {
        return delayDuration;
    }

    public void setDelayDuration(Date delayDuration) {
        this.delayDuration = delayDuration;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Pigeon> getPigeons() {
        return pigeons;
    }

    public void setPigeons(List<Pigeon> pigeons) {
        this.pigeons = pigeons;
    }
}
