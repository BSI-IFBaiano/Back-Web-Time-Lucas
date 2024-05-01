package com.web.desenvolvimento.edusphere.Controller;

import com.web.desenvolvimento.edusphere.DTO.UserDTO;
import com.web.desenvolvimento.edusphere.Model.User.User;
import com.web.desenvolvimento.edusphere.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<UserDTO> findById(@PathVariable("id") Long idUser) {
        return userService.findById(idUser);
    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid UserDTO userDTO) {
        return userService.create(userDTO);
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody @Valid UserDTO userDTO) throws Exception {
        return userService.update(userDTO);
    }
}
