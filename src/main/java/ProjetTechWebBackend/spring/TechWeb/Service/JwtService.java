package ProjetTechWebBackend.spring.TechWeb.Service;

import ProjetTechWebBackend.spring.TechWeb.Entity.JwtRequest;
import ProjetTechWebBackend.spring.TechWeb.Entity.JwtResponse;
import ProjetTechWebBackend.spring.TechWeb.Repository.UserRepository;
import ProjetTechWebBackend.spring.TechWeb.Util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class JwtService {


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    // Méthode pour authentifier et générer le token JWT
    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userEmail = jwtRequest.getUserEmail();

        // Authentifier l'utilisateur avec son email et mot de passe
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userEmail, jwtRequest.getUserPassword())
        );

        // Charger les détails de l'utilisateur
        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);

        // Générer le token JWT
        String token = jwtUtil.generateToken(userDetails);

        // Retourner la réponse avec le token
        return new JwtResponse(token);
    }


}
