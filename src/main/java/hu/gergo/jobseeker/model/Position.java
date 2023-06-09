package hu.gergo.jobseeker.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A megnevezés kötelező")
    @Size(max = 50, message = "A megnevezés legfeljebb 50 karakter lehet")
    private String name;

    @NotBlank(message = "A földrajzi hely kötelező")
    @Size(max = 50, message = "A földrajzi hely legfeljebb 50 karakter lehet")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
