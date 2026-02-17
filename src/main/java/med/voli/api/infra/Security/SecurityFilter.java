package med.voli.api.infra.Security;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import med.voli.api.domain.Usuario.UsuarioRepository;

@Component
public class SecurityFilter extends OncePerRequestFilter {
   public static final String AMARELO = "\u001B[33m";

   @Autowired
   private TokenService tokenService;

   @Autowired
   private UsuarioRepository usuarioRepository;

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
         throws ServletException, IOException {
      var tokenJWT = recuperarToken(request);

      if (tokenJWT != null) {
         var subject = tokenService.getSubject(tokenJWT);
         var usuario = usuarioRepository.findByLogin(subject);

         var authentication = new UsernamePasswordAuthenticationToken(usuario,null,usuario.getAuthorities());

         SecurityContextHolder.getContext().setAuthentication(authentication);
      }

      filterChain.doFilter(request, response);
   }

   private String recuperarToken(HttpServletRequest request) {
      var authorizationHeader = request.getHeader("Authorization");
      if (authorizationHeader == null) {
         throw new RuntimeException("Token não enviado");
      }

      return authorizationHeader.replace("Bearer ", "");
   }

}
