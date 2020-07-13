package restful.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author OS
 */
public class JWTToken {

      private String idToken,message,username;
      private int status;

        public JWTToken(String idToken, String message, int status, String username) {
            this.idToken = idToken;
            this.message = message;
            this.status = status;
            this.username = username;
        }

      @JsonProperty("id_token")
      String getIdToken() {
         return idToken;
      }

      void setIdToken(String idToken) {
         this.idToken = idToken;
      }
        @JsonProperty("message")
        public String getMessage() {
            return message;
        }
        
        public void setMessage(String message) {
            this.message = message;
        }
        @JsonProperty("status")
        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
        @JsonProperty("username")
        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
      
   }
