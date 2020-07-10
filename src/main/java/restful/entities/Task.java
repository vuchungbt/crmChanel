package restful.entities;

import java.time.LocalDateTime;
import java.util.Date;
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
@Document(collection="Task")
public class Task {
    @Id
    private String id;
    
    @Field
    @Size(max = 10)
    private String taskID;
    
    @Field
    @Size(max = 120)
    private String name;
    
    @DBRef
    private User createdBy;
    
    @Field(value = "created")
    @CreatedDate
    private LocalDateTime created;
    
    @Field
    private Date dueDate;
    
    @Field
    private String updatedDate;
    
    @Field
    @Size(max = 1000)
    private String notes;
    
    //Description
    @Field
    @Size(max = 1000)
    private String description;
    
    @Field
    @Size(max = 30)
    private String status;
    
    @Field
    @Size(max = 30)
    private double completePercent;
    
     //Priority
    @Field
    @Size(max = 20)
    private String priority;
    
    @DBRef
    private Set<User> assignTo = new HashSet<>();
    @DBRef
    private Set<Work> work = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getCompletePercent() {
        return completePercent;
    }

    public void setCompletePercent(double completePercent) {
        this.completePercent = completePercent;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Set<User> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(Set<User> assignTo) {
        this.assignTo = assignTo;
    }

    public Set<Work> getWork() {
        return work;
    }

    public void setWork(Set<Work> work) {
        this.work = work;
    }
    
    
}
