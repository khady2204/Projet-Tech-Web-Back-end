package ProjetTechWebBackend.spring.TechWeb.Controller;

import ProjetTechWebBackend.spring.TechWeb.Entity.JwtRequest;
import ProjetTechWebBackend.spring.TechWeb.Entity.JwtResponse;
import ProjetTechWebBackend.spring.TechWeb.Service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class JwtController {

    @Autowired
    private JwtService jwtService;

    @PostMapping({"/authenticate"})
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
