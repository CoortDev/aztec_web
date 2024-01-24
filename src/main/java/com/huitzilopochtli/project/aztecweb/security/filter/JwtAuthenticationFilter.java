package com.huitzilopochtli.project.aztecweb.security.filter;

import static com.huitzilopochtli.project.aztecweb.security.TokenJwtConfig.CONTENT_TYPE;
import static com.huitzilopochtli.project.aztecweb.security.TokenJwtConfig.HEADER_AUTHORIZATION;
import static com.huitzilopochtli.project.aztecweb.security.TokenJwtConfig.PREFIX_TOKEN;
import static com.huitzilopochtli.project.aztecweb.security.TokenJwtConfig.SECRET_KEY;

import java.io.IOException;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.huitzilopochtli.project.aztecweb.entities.UserEntity;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
                
                UserEntity user = null;
                String username = null;
                String password = null;

                try {
                    user = new ObjectMapper().readValue(request.getInputStream(), UserEntity.class);
                    username = user.getUserName();
                    password = user.getPass();
                } catch (StreamReadException e) {
                    e.printStackTrace();
                } catch (DatabindException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
                return authenticationManager.authenticate(authenticationToken);
    }

    /**
     * *Método que especifica el token cuando el login ha salido bien
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {
                User user = (User) authResult.getPrincipal();
                String userName = user.getUsername();

                /**
                 * * getAuthorities() permite obtener los roles vinculados a un usuario
                 */
                Collection<? extends GrantedAuthority> roles = authResult.getAuthorities();

                Claims claims = Jwts.claims().add("authorities", new ObjectMapper().writeValueAsString(roles)).build();

                String token = Jwts.builder()
                        .subject(userName)
                        .claims(claims)
                        .expiration(new Date(System.currentTimeMillis()+7200000))
                        .issuedAt(new Date())
                        .signWith(SECRET_KEY)
                        .compact();

                response.addHeader(HEADER_AUTHORIZATION, PREFIX_TOKEN+token);
                
                Map<String, String> body = new HashMap<>();
                body.put("token", token);
                body.put("userName", userName);
                body.put("message", String.format("Hola %s has iniciado sesión con exito", userName));

                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setContentType(CONTENT_TYPE);
                response.setStatus(200);

    }

    /**
     * !Metodo que interviene cuando algo ha salido mal en el login
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
                Map<String, String> body = new HashMap<>();
                body.put("message", "Error en la autenticación: User name y/o Password incorrectos!");
                body.put("error", failed.getMessage());
                
                response.getWriter().write(new ObjectMapper().writeValueAsString(body));
                response.setStatus(401);
                response.setContentType(CONTENT_TYPE);
    }
}
