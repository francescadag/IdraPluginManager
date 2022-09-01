package IdraPluginManager.model;

import java.util.List;

import IdraPluginManager.pluginParamethers.model.PluginParamethersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PluginDTO {

	private String name;
	//private PluginType type;
	//private int status;
	//private String method;
	private String link;
	private String description;
	private PluginParamethersDTO paramethers;
	private List<String> compatibleFormats;


}
