package IdraPluginManager.model;

import java.io.Serializable;
import java.util.List;

import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import IdraPluginManager.pluginParamethers.model.PluginParamethersDTO;
import IdraPluginManager.returnedObject.model.ReturnedObjectDTO;
import IdraPluginManager.utils.AutoGenObjectID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "plugins")
@TypeAlias("plugins")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Plugin 
implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 430059758967494038L;

//	@Id
//	@AutoGenObjectID
//	private ObjectId pluginId;
	
	@Id
	@AutoGenObjectID
	@CreatedBy
	private ObjectId id;
	
	private String name;
	private String description;
	private PluginStatus status;
	private PluginType type;
	private String link;
	private PluginMethod method;
	private PluginParamethersDTO paramethers;
	private List<String> compatibleFormats;
	
	
	@LastModifiedBy
	private String modifiedBy;
	
	@CreatedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private DateTime createdAt;
	
	@LastModifiedDate
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
	private DateTime modifiedAt;
	
	public String getId() {
		return this.id.toHexString();
	}
	
	@JsonIgnore
	public ObjectId getIdObjectId() {
		return this.id;
	}


}
