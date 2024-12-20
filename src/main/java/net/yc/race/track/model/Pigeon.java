package net.yc.race.track.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "pigeons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pigeon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Badge number cannot be blank.")
    @Pattern(regexp = "^\\d{4}$", message = "Badge number must be a 4-digit number.")
    @Column(nullable = false)
    private String numeroDeBadge;

    @Min(value = 1, message = "Age must be greater than 0.")
    @Max(value = 30, message = "Age cannot be greater than 30.")
    @Column(nullable = false)
    private int age;

    @NotBlank(message = "Color cannot be blank.")
    @Size(max = 50, message = "Color cannot exceed 50 characters.")
    @Column(nullable = false)
    private String couleur;

    @ManyToOne
    @NotNull(message = "competition ID is required.")
    @JoinColumn(name = "competition_id", nullable = false)
    private Competition competition;

    @OneToMany(mappedBy = "pigeon", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Result> results;

    @ManyToOne
    @NotNull(message = "User ID is required.")
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroDeBadge() {
        return numeroDeBadge;
    }

    public void setNumeroDeBadge(String numeroDeBadge) {
        this.numeroDeBadge = numeroDeBadge;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
