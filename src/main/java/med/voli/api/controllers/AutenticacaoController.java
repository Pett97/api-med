package med.voli.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import med.voli.api.domain.Usuario.DtoLogin;
import med.voli.api.domain.Usuario.Usuario;
import med.voli.api.infra.Security.DtoTokenJWT;
import med.voli.api.infra.Security.TokenService;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

   @Autowired
   private AuthenticationManager authenticationManager;

   @Autowired
   private TokenService tokenService;

   @PostMapping
   public ResponseEntity login(@RequestBody @Valid DtoLogin dtoLogin){
      var authenticationToken = new UsernamePasswordAuthenticationToken(dtoLogin.login(),dtoLogin.senha());
      var authenticate = authenticationManager.authenticate(authenticationToken);
      var tokenJWT = tokenService.GerarToken((Usuario)authenticate.getPrincipal());
      return ResponseEntity.ok(new DtoTokenJWT(tokenJWT));
   }
}
