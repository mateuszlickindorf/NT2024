package com.mateusz.library.library.controller.user;
import com.mateusz.library.library.controller.dto.user.GetUserFullDto;
import com.mateusz.library.library.service.user.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@Tag(name = "User")
@CrossOrigin
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        userService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GetUserFullDto> getById(@PathVariable long id) {
        GetUserFullDto getUserDTO = userService.getById(id);
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/get/me")
    public ResponseEntity<GetUserFullDto> getByUsername(Principal principal) {
        String username = principal.getName();
        GetUserFullDto getUserDTO = userService.getByUsername(username);
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }

    @GetMapping("/get")
    public @ResponseBody ResponseEntity<List<GetUserFullDto>> getAll() {
        List<GetUserFullDto> getUserDTO = userService.getAll();
        return new ResponseEntity<>(getUserDTO, HttpStatus.OK);
    }
}
