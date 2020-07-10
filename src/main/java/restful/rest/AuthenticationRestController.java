package restful.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import restful.dto.UserDTO;
import restful.security.jwt.JWTFilter;
import restful.security.jwt.TokenProvider;

/**
 * Controller to authenticate users.
 */
@RestController
@RequestMapping("/api")
public class AuthenticationRestController {
    
    private final Logger log = LoggerFactory.getLogger(AuthenticationRestController.class);

   private final TokenProvider tokenProvider;
   
   private AuthenticationManagerBuilder authenticationManagerBuilder;

   public AuthenticationRestController(TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
      this.tokenProvider = tokenProvider;
      this.authenticationManagerBuilder = authenticationManagerBuilder;
   }

  
    @PostMapping("/auth")
   public ResponseEntity<JWTToken> authorize(@Valid @RequestBody UserDTO loginDto) {

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
        
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        boolean rememberMe = (loginDto.isRememberMe() == null) ? false : loginDto.isRememberMe();
        
        String jwt = tokenProvider.createToken(authentication, rememberMe);
        
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(JWTFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

        return new ResponseEntity<>(new JWTToken(jwt), httpHeaders, HttpStatus.OK);
   }

   /**
    * Object to return as body in JWT Authentication.
    */
   static class JWTToken {

      private String idToken;

      JWTToken(String idToken) {
         this.idToken = idToken;
      }

      @JsonProperty("id_token")
      String getIdToken() {
         return idToken;
      }

      void setIdToken(String idToken) {
         this.idToken = idToken;
      }
   }
}
