package restful.entities;

import java.util.Objects;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * @author OS
 */
@Document(collection="Authority")
public class Authority {
    
    @Id
    @Field
    @NotNull
    private String name;

    public Authority(String name) {
        this.name = name;
    }

    public Authority() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Authority authority = (Authority) o;
      return name == authority.name;
   }

   @Override
   public int hashCode() {
      return Objects.hash(name);
   }

   @Override
   public String toString() {
      return "Authority{" +
         "name=" + name +
         '}';
   }

}
