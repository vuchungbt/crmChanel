package restful.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Size;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 *
 * developer VuChung
 */
@Document(collection="Channel")
public class Channel {
    @Id
    private String id;
    
    @Field
    @Size(max = 10)
    private String channelID;
    
    @Field
    @Size(max = 200)
    private String name;
    
    @Field
    @Size(max = 200)
    private String source;
    
    @Field(value = "created")
    @CreatedDate
    private LocalDateTime created;
    
    @Field
    @Size(max = 30)
    private String status="Not Started";
   
    @Field
    @Size(max = 30)
    private String category;
    
    @DBRef
    private User createdBy;
    
    @Field
    @Size(max = 100)
    private String modifiedBy;
    
    @DBRef
    private User owner;
    
    //Description
    @Field
    @Size(max = 1000)
    private String description;
    
    @DBRef
    private Set<User> assignTo = new HashSet<>();
    
    @DBRef
    private Set<Task> task = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChannelID() {
        return channelID;
    }

    public void setChannelID(String channelID) {
        this.channelID = channelID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(Set<User> assignTo) {
        this.assignTo = assignTo;
    }

    public Set<Task> getTask() {
        return task;
    }

    public void setTask(Set<Task> task) {
        this.task = task;
    }

    
}
