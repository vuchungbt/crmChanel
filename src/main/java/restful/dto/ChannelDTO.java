package restful.dto;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * developer VuChung
 */
public class ChannelDTO {
    
    private String channelID;

    private String name;
    
    private String source;
   
    private String category;

    private String createdBy;

    private String modifiedBy;

    private String owner;
    
    private String description;

    private Set<String> assignTo = new HashSet<>();
    
    private Set<String> task = new HashSet<>();

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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getAssignTo() {
        return assignTo;
    }

    public void setAssignTo(Set<String> assignTo) {
        this.assignTo = assignTo;
    }

    public Set<String> getTask() {
        return task;
    }

    public void setTask(Set<String> task) {
        this.task = task;
    }

    
}
