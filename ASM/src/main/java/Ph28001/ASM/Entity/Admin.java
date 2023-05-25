package Ph28001.ASM.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int adminId;
    private String adminName;
    private String adminPassword;
    private String adminSdt;
    private String adminRole;
}
