package IdraPluginManager.returnedObject.model;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnedObjectDTO {
	@NotBlank
	private ReturnedObject returnedObject;
}
