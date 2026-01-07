package com.aleyna.userloginprofiles.controller;

import com.aleyna.userloginprofiles.dto.LoginRequest;
import com.aleyna.userloginprofiles.entity.User;
import com.aleyna.userloginprofiles.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@Tag(name = "PROFİL YÖNETİMİ", description = "Kullanıcının kendi profil yönetimi")
public class UserLoginProfilesController {

    private final UserService userService;

    public UserLoginProfilesController(UserService userService) {
        this.userService = userService;
    }

    // --- KULLANICI GETİR (Kendi Profili) ---
    @GetMapping("/{id}")
    @Operation(summary = "Kendi kullanıcı profilini getir")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- KULLANICI GÜNCELLE (Kendi Profili) ---
    @PutMapping("/{id}")
    @Operation(summary = "Kendi kullanıcı profilini güncelle")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user) {
        boolean updated = userService.updateUser(id, user);
        if (updated) {
            return ResponseEntity.ok("Profil güncellendi");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // --- GİRİŞ ---
    @PostMapping("/login")
    @Operation(summary = "Kullanıcı giriş")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        boolean success = userService.login(request.getTcKimlikNo(), request.getPassword());
        if (success) {
            return ResponseEntity.ok("Giriş başarılı");
        } else {
            return ResponseEntity.status(401).body("Giriş başarısız");
        }
    }

    // --- KAYIT ---
    @PostMapping("/register")
    @Operation(summary = "Yeni kullanıcı kaydı")
    public ResponseEntity<String> register(@RequestBody User user) {
        boolean created = userService.register(user);
        if (created) {
            return ResponseEntity.ok("Kayıt başarılı");
        } else {
            return ResponseEntity.badRequest().body("Kayıt başarısız");
        }
    }
}
