package net.yc.race.track.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import net.yc.race.track.Enum.Role;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @jakarta.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password cannot be blank.")
    @Size(min = 8, message = "Password must be at least 8 characters long.")
    @Column(nullable = false)
    private String password;

    @NotBlank(message = "Loft name cannot be blank.")
    @Size(max = 100, message = "Loft name cannot exceed 100 characters.")
    @Column(nullable = false)
    private String loftName;

    @NotBlank(message = "GPS coordinates cannot be blank.")
    @Pattern(
            regexp = "^[-+]?\\d{1,2}\\.\\d+,[-+]?\\d{1,3}\\.\\d+$",
            message = "GPS coordinates must be in the format 'latitude,longitude'."
    )
    @Column(nullable = false)
    private String gpsCoordinates;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.LAZY)
    private List<Pigeon> Pigeons;

    @Enumerated(EnumType.STRING)
    private Role role = Role.ROLE_USER;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoftName() {
        return loftName;
    }

    public void setLoftName(String loftName) {
        this.loftName = loftName;
    }

    public String getGpsCoordinates() {
        return gpsCoordinates;
    }

    public void setGpsCoordinates(String gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public List<Pigeon> getPigeons() {
        return Pigeons;
    }

    public void setPigeons(List<Pigeon> pigeons) {
        Pigeons = pigeons;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
