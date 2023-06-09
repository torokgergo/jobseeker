package hu.gergo.jobseeker.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "A név kötelező")
    @Size(max = 100, message = "A név legfeljebb 100 karakter lehet")
    private String name;

    @NotBlank(message = "Az e-mail cím kötelező")
    @Email(message = "Érvénytelen e-mail cím formátum")
    @Column(unique = true)
    private String email;

    private final UUID apiKey;

    public Client() {
        apiKey = UUID.randomUUID();
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getApiKey() {
        return apiKey;
    }
}
