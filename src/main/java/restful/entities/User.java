package restful.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="Users")
public class User {
    @Id
    private String id;
    @Field
    @Size(max = 50)
    private String firstname;
    @Field
    @Size(max = 20)
    private String lastname;
    @Field
    @Size(max = 120)
    private String email;
    @Field
    @Size(max = 50)
    private String username;
    
    @Field
    @Size(max = 120)
    @JsonIgnore
    private String password;
    
    @Field
    private boolean activated = true;
    
    @Field(value = "created")
    @CreatedDate
    private LocalDateTime created;

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
    
    public String getLastname() {
        return lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    

    public User() {
    }
    
    private Set<Authority> authorities = new HashSet<>();
    
    public Set<Authority> getAuthorities() {
       return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
       this.authorities = authorities;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + activated +
                '}';
    }
}
