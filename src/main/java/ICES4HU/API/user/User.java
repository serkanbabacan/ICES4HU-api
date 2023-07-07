package ICES4HU.API.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "users")
public class User extends BaseEntity {

    private @Id Long id;
    private String user_id;
    private String first_name;
    private String last_name;
    private String email;
    private Boolean banned = false;
    private String userType = "student";
}
