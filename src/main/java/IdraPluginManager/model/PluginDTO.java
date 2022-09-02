package IdraPluginManager.model;

import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;

import IdraPluginManager.paramethers.model.ParametersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PluginDTO {

	private String name;
	@Indexed(unique=true)
	private String url;
	private String description;
	private ParametersDTO paramethers;
	private List<String> compatibleFormats;


}
