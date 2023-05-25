package Ph28001.ASM.Controller;

import Ph28001.ASM.Entity.Admin;
import Ph28001.ASM.Entity.User;
import Ph28001.ASM.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private AdminRepository adminRepository;
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String listAdmin(Model model){
        List<Admin>adminList= adminRepository.findAll();
        model.addAttribute("adminList",adminList);
        return "admin";
    }
    @RequestMapping(value = "newAdmin")
    public  String newAdmin(Map<String,Admin>model){
        Admin admin = new Admin();
        model.put("admin",admin);
        return "newAdmin";
    }
    @RequestMapping(value = "admin/saveAdmin",method = RequestMethod.POST)
    public String save(Admin admin){
        adminRepository.save(admin);
        return "redirect:/admin";
    }
    @RequestMapping("admin/edit")
    public ModelAndView editAdmin(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit_admin");
        Admin admin = adminRepository.findAdminByAdminId(id);
        modelAndView.addObject("admin",admin);
        return modelAndView;

    }
    @RequestMapping("admin/delete")
    public String deleteAdmin(@RequestParam int id){
        adminRepository.deleteById(id);
        return "redirect:/admin";
    }
}
