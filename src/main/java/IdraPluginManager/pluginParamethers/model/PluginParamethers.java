package IdraPluginManager.pluginParamethers.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PluginParamethers {

	private Map<String, Object> map = new HashMap<String, Object>();

	@JsonAnyGetter
    public Map<String, Object> getMap() {
        return map;
    }

    @JsonAnySetter
    public void setMap(String name, Object value) {
    	map.put(name, value);
    }
}
