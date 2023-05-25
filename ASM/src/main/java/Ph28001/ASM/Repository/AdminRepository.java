package Ph28001.ASM.Repository;

import Ph28001.ASM.Entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
    public Admin findAdminByAdminId(Integer id);

    public Admin findAllByAdminName(String name);
}
