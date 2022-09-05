package IdraPluginManager.controller;

import java.util.List;
import javax.validation.Valid;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import IdraPluginManager.exceptions.BadRequestException;
import IdraPluginManager.model.Plugin;
import IdraPluginManager.model.PluginDTO;
import IdraPluginManager.model.PluginMethod;
import IdraPluginManager.model.PluginStatus;
import IdraPluginManager.model.PluginType;
import IdraPluginManager.service.IdraPluginServiceImpl;
import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping(value = "/plugin")
public class IdraPluginController {

	@Autowired
	private IdraPluginServiceImpl s;

	@GetMapping
	@CrossOrigin(origins = {"${idra.basepath}"})
	private List<Plugin> getAllPlugins(){
		log.debug("Get All Idra plug-ins");
		return s.getAllPlugins();
	}
	
	@GetMapping("/{id}")
	@CrossOrigin(origins = {"${idra.basepath}"})
	private Plugin getPlugin(@PathVariable(name="id") String id){
		log.info("Get Plugin by Id");
		if(!ObjectId.isValid(id)) {
			throw new BadRequestException("Invalid plug-in id provided: " + id);
		}
		return s.getPlugin(new ObjectId(id));
	}

	@PostMapping
	@CrossOrigin(origins = {"${idra.basepath}"})
	private Plugin createPlugin(@RequestBody @Valid PluginDTO plugin, @RequestParam(name="type") PluginType type, 
			@RequestParam(name="status") PluginStatus status, @RequestParam(name="method")  PluginMethod method){
		log.info("Register Plugin");
		return s.addPlugin(plugin.getName(), plugin.getDescription(),
				plugin.getUrl(), plugin.getParameters(), type, status, method, plugin.getCompatibleFormats());
	}
	
	@PutMapping("/{id}")
	@CrossOrigin(origins = {"${idra.basepath}"})
	private Plugin updatePlugin(@PathVariable(name="id") String id, @RequestBody @Valid PluginDTO plugin, @RequestParam(name="type") PluginType type,
			@RequestParam(name="status") PluginStatus status, @RequestParam(name="method")  PluginMethod method){
		log.info("Update Plugin by Id");
		if(!ObjectId.isValid(id)) {
			throw new BadRequestException("Invalid plug-in id provided: " + id);
		}
		return s.updatePlugin(new ObjectId(id), plugin.getName(), plugin.getDescription(), type,
				plugin.getUrl(), status, method, plugin.getCompatibleFormats(), plugin.getParameters());
	}

	@DeleteMapping("/{id}")
	@CrossOrigin(origins = {"${idra.basepath}"})
	private ResponseEntity<?> deletePlugin(@PathVariable(name="id") String id){
		log.info("Delete Plugin by Id");
		if(!ObjectId.isValid(id)) {
			throw new BadRequestException("Invalid device id provided: " + id);
		}
		boolean deleted = s.deletePlugin(new ObjectId(id));
		if (deleted)
			return ResponseEntity.ok().build();
		else
			return ResponseEntity.status(500).build();
	}
	


}

