package net.yc.race.track.DTO.ResponseDTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.yc.race.track.DTO.EmbededDTO.PigeonEmbeded;
import net.yc.race.track.model.Pigeon;
import net.yc.race.track.model.Result;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class UserResponse {
    private Long id;

    @NotBlank(message = "Username cannot be blank.")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters.")
    @Column(nullable = false)
    private String username;

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

    private List<PigeonEmbeded> pigeons;

}
