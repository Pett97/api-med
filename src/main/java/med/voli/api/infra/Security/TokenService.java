package med.voli.api.infra.Security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import med.voli.api.domain.Usuario.Usuario;

@Service
public class TokenService {

   @Value("${api.security.token.secret}")
   private String secret;

   public String GerarToken(Usuario usuario) {
      try {
         var algoritmo = Algorithm.HMAC256(secret);
         return JWT.create()
               .withIssuer("API Voli.api")
               .withSubject(usuario.getLogin())
               .withExpiresAt(dataExpiracao())
               .withClaim("id", usuario.getId())// informacoes tipo nivel de acesso master adm cliente e etc
               .sign(algoritmo);
      } catch (JWTCreationException exception) {
         throw new RuntimeException("erro ao gerar token JWT", exception);
      }
   }

   private Instant dataExpiracao() {
      return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
   }

   public String getSubject(String tokenJWT) {
      try {
         var algoritimo = Algorithm.HMAC256(secret);
         return JWT.require(algoritimo)
               .withIssuer("API Voli.api")
               .build()
               .verify(tokenJWT)
               .getSubject();
      } catch (JWTVerificationException e) {
            throw new RuntimeException("TOKEN JWT INVALIDO ou EXPIRADO");
      }
   }
}
