package IdraPluginManager.paramethers.model;

import IdraPluginManager.model.IdraResourceType;
import IdraPluginManager.model.ParametersType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameters {

	private String idraParameter;
	private String serviceParameter;
	private ParametersType parametersType;
	private IdraResourceType idraResourceType;
	
}
