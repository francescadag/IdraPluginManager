package IdraPluginManager.service;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import IdraPluginManager.exceptions.BadRequestException;
import IdraPluginManager.exceptions.ResourceNotFoundException;
import IdraPluginManager.model.Plugin;
import IdraPluginManager.model.PluginMethod;
import IdraPluginManager.model.PluginStatus;
import IdraPluginManager.model.PluginType;
import IdraPluginManager.paramethers.model.Parameters;
import IdraPluginManager.repository.PluginRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IdraPluginServiceImpl implements IdraPluginService {
	
	@Autowired
	PluginRepository pluginRepo ;

	@Override
	public Plugin addPlugin(String name, String description, String link, List<Parameters> parameters, 
			PluginType type, PluginStatus status, PluginMethod method, List<String> compatibleFormats) {
		
		if (existsPluginName(name)) {
		throw new BadRequestException("Unable to create thve plugin, an instance of " + name
				+ " already exists!");
		}

		Plugin p = new Plugin();
		p.setName(name);
		p.setDescription(description);
		p.setUrl(link);
		p.setStatus(status);
		p.setMethod(method);
		p.setParameters(parameters);
		p.setType(type);
		p.setCompatibleFormats(compatibleFormats);

		p = pluginRepo.save(p);
		log.info("Plugin with name " + name + " created");
				
		return p;
	}
	
	@Override
	public boolean deletePlugin(ObjectId id) {
		Plugin p = getPlugin(id);
		
		try {
			pluginRepo.delete(p);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}
	
	@Override
	public Plugin getPlugin(ObjectId id) {
		log.debug("Read Plugin by id "+id);
		return pluginRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plugin with id: " + id + " not found"));
	}
	
	@Override
	public List<Plugin> getAllPlugins() {
		log.debug("Get All Idra Plugins");
		return pluginRepo.findAll();
	}
	
	@Override
	public Plugin updatePlugin(ObjectId id, String name, String description, PluginType type,
			String link, PluginStatus status, PluginMethod method, List<String> compatibleFormats, List<Parameters> parameters) {
		log.debug("Update Plugin by id "+id);
		Plugin p = pluginRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plugin with id: " + id + " not found"));
		
		p.setName(name);
		p.setDescription(description);
		p.setStatus(status);
		p.setType(type);
		p.setUrl(link);
		p.setMethod(method);
		p.setCompatibleFormats(compatibleFormats);
		p.setParameters(parameters);
		
		return pluginRepo.save(p);
	}
	
	@Override
	public Plugin enablePlugin(ObjectId pluginId, PluginStatus enable) {
		Plugin p = getPlugin(pluginId);
		log.info("Changing the status for " + pluginId + " with value: " + enable);
		p.setStatus(enable);
		return pluginRepo.save(p);
	}

	
	private boolean existsPluginName(String name) {
		return pluginRepo.findByName(name).isPresent();
	}

}
