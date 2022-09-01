package IdraPluginManager.service;

import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import IdraPluginManager.exceptions.BadRequestException;
import IdraPluginManager.exceptions.ResourceNotFoundException;
import IdraPluginManager.model.Plugin;
import IdraPluginManager.model.PluginMethod;
import IdraPluginManager.model.PluginStatus;
import IdraPluginManager.model.PluginType;
import IdraPluginManager.pluginParamethers.model.PluginParamethersDTO;
import IdraPluginManager.repository.PluginRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class IdraPluginServiceImpl implements IdraPluginService {
	
	@Autowired
	PluginRepository pluginRepo ;

	@Override
	public Plugin addPlugin(String name, String description, String link, PluginParamethersDTO paramethers, 
			PluginType type, PluginStatus status, PluginMethod method, List<String> compatibleFormats) {
		
		if (existsPluginName(name)) {
		throw new BadRequestException("Unable to create thve plugin, an instance of " + name
				+ " already exists!");
		}

		Plugin p = new Plugin();
		p.setName(name);
		p.setDescription(description);
		p.setLink(link);
		p.setStatus(status);
		p.setMethod(method);
		p.setParamethers(paramethers);
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
	public Plugin updatePlugin(ObjectId id, String name, String description, PluginType type,
			String link, PluginStatus status, PluginMethod method, List<String> compatibleFormats) {
		log.debug("Update Plugin by id "+id);
		Plugin p = pluginRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Plugin with id: " + id + " not found"));
		
		p.setName(name);
		p.setDescription(description);
		p.setStatus(status);
		p.setType(type);
		p.setLink(link);
		p.setMethod(method);
		p.setCompatibleFormats(compatibleFormats);
		
		return pluginRepo.save(p);
	}

	
	private boolean existsPluginName(String name) {
		return pluginRepo.findByName(name).isPresent();
	}

}
