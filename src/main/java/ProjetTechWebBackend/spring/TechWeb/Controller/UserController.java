package ProjetTechWebBackend.spring.TechWeb.Controller;

import ProjetTechWebBackend.spring.TechWeb.Entity.Role;
import ProjetTechWebBackend.spring.TechWeb.Entity.User;
import ProjetTechWebBackend.spring.TechWeb.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Récupérer tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Récupérer un utilisateur par ID
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Supprimer un utilisateur
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // Ajouter un étudiant
    @PostMapping("/etudiants")
    public User addEtudiant(@RequestBody User etudiant) {
        return userService.addEtudiant(etudiant);
    }

    // Ajouter un admin (optionnel)
    @PostMapping("/admins")
    public User addAdmin(@RequestBody User admin) {
        return userService.addAdmin(admin);
    }

    // Liste des étudiants
    @GetMapping("/etudiants")
    public List<User> getAllEtudiants() {
        return userService.getUsersByRole(Role.ETUDIANT);
    }

    // Liste des admins
    @GetMapping("/admins")
    public List<User> getAllAdmins() {
        return userService.getUsersByRole(Role.ADMIN);
    }



}
