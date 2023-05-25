package Ph28001.ASM.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String userName; //UserName
    private String password; //Password
    private String role; //Role
    private String name; //Name
}
