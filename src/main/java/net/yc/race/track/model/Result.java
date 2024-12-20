package net.yc.race.track.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "results")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Min(value = 1, message = "Rank must be a positive number.")
    @Column(nullable = false)
    private int rank;

    @NotBlank(message = "Loft name cannot be blank.")
    @Size(max = 100, message = "Loft name cannot exceed 100 characters.")
    @Column(nullable = false)
    private String loftName;

    @NotBlank(message = "Badge number cannot be blank.")
    @Pattern(regexp = "^\\d{4}$", message = "Badge number must be a 4-digit number.")
    @Column(nullable = false)
    private String numeroDeBadge;

    @NotNull(message = "Arrival hour cannot be null.")
    @Column(nullable = false)
    private Date arriveHour;

    @Min(value = 0, message = "Distance must be a positive number.")
    @Column(nullable = false)
    private double distance;

    @Min(value = 0, message = "Speed must be a positive number.")
    @Column(nullable = false)
    private double speed;

    @Min(value = 0, message = "Points must be a positive number.")
    @Column(nullable = false)
    private double point;

    @Min(value = 0, message = "Adjusted speed must be a positive number.")
    @Column(nullable = false)
    private double adjustedSpeed;

    @ManyToOne
    @NotNull(message = "Pigeon is required.")
    @JoinColumn(name = "pigeon_id", nullable = false)
    private Pigeon pigeon;

    @ManyToOne
    @NotNull(message = "Competition is required.")
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getLoftName() {
        return loftName;
    }

    public void setLoftName(String loftName) {
        this.loftName = loftName;
    }

    public String getNumeroDeBadge() {
        return numeroDeBadge;
    }

    public void setNumeroDeBadge(String numeroDeBadge) {
        this.numeroDeBadge = numeroDeBadge;
    }

    public Date getArriveHour() {
        return arriveHour;
    }

    public void setArriveHour(Date arriveHour) {
        this.arriveHour = arriveHour;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public double getAdjustedSpeed() {
        return adjustedSpeed;
    }

    public void setAdjustedSpeed(double adjustedSpeed) {
        this.adjustedSpeed = adjustedSpeed;
    }

    public Pigeon getPigeon() {
        return pigeon;
    }

    public void setPigeon(Pigeon pigeon) {
        this.pigeon = pigeon;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }
}