package IdraPluginManager.paramethers.model;

import java.util.List;

import IdraPluginManager.model.ParametersType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Parameters {

	private List<String> idraParameters;
	private List<String> serviceParameters;
	private ParametersType parametersType;
	
}
