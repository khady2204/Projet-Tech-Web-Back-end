package ProjetTechWebBackend.spring.TechWeb.Service;

import ProjetTechWebBackend.spring.TechWeb.Entity.Role;
import ProjetTechWebBackend.spring.TechWeb.Entity.User;
import ProjetTechWebBackend.spring.TechWeb.Repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    // Injection automatique du repository qui permet d'accéder à la base de données
    @Autowired
    private UserRepository userRepository;

    // Injection du bean PasswordEncoder pour encoder (hasher) les mots de passe
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Ajouter un étudiant
    public User addEtudiant(User etudiant) {
        etudiant.setRole(Role.ETUDIANT);
        etudiant.setUserPassword(passwordEncoder.encode(etudiant.getUserPassword()));
        return userRepository.save(etudiant);
    }

    // Ajouter un admin
    public User addAdmin(User admin) {
        admin.setRole(Role.ADMIN);
        admin.setUserPassword(passwordEncoder.encode(admin.getUserPassword()));
        return userRepository.save(admin);
    }

    // Obtenir tous les utilisateurs
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Obtenir un utilisateur par ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Supprimer un utilisateur
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Obtenir les utilisateurs selon le rôle
    public List<User> getUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // Initialisation automatique d'un admin
    @PostConstruct
    public void initAdmin() {
        if (userRepository.findByRole(Role.ADMIN).isEmpty()) {
            User admin = new User();
            admin.setUserNom("Admin");
            admin.setUserPrenom("User");
            admin.setUserEmail("admin@admin.com");
            admin.setUserPassword(passwordEncoder.encode("admin123"));
            admin.setRole(Role.ADMIN);
            userRepository.save(admin);
        }
    }
}
