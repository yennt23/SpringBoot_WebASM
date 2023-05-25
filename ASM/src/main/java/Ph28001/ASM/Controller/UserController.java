package Ph28001.ASM.Controller;

import Ph28001.ASM.Repository.UserRepository;
import Ph28001.ASM.Entity.User;
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
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/index")
    public String home(){
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String listUser(Model m){
        List<User> listUsers = userRepository.findAll();
        m.addAttribute("listUsers", listUsers);
        return "users";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

    @RequestMapping(value = "/new")
    public String newUsers(Map<String, User>m){
        User user = new User();
        m.put("user", user);
        return "new";
    }

    @RequestMapping(value = "user/save", method = RequestMethod.POST)
    public String save(User user) {
        userRepository.save(user);
        return "redirect:/user";
    }

    @RequestMapping("/edit")
    public ModelAndView editUser(@RequestParam int id){
        ModelAndView modelAndView = new ModelAndView("edit_user");
        User user = userRepository.findOneById(id);
        modelAndView.addObject("user",user);
        return modelAndView;

    }
    @RequestMapping("/delete")
    public String deleteUser(@RequestParam int id){
        userRepository.deleteById(id);
        return "redirect:/user";
    }
}
