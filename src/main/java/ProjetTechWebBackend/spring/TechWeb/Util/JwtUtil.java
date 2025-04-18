package ProjetTechWebBackend.spring.TechWeb.Util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import java.util.Date;

@Component
public class JwtUtil {

    // Clé secrète pour signer et valider les tokens JWT
    private String secretKey = "secret_tech_web";

    // Générer un token JWT à partir d'un UserDetails
    public String generateToken(UserDetails userDetails) {
        return generateToken(userDetails.getUsername());
    }

    // Méthode pour générer un token JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))  // Expiration dans 10 heures
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // Méthode pour extraire le nom d'utilisateur du token JWT
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }

    // Méthode pour extraire les claims (informations) du token JWT
    private Claims extractClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody();
    }

    // Méthode pour vérifier si le token est expiré
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    // Méthode pour valider le token
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
