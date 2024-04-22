package com.web.desenvolvimento.edusphere.Controller;

import com.web.desenvolvimento.edusphere.Model.UserModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/get")
    public String get(){
        return "Criado";
    }
}
