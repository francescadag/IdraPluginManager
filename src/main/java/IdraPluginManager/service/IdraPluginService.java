package IdraPluginManager.service;

import java.util.List;

import org.bson.types.ObjectId;

import IdraPluginManager.model.Plugin;
import IdraPluginManager.model.PluginMethod;
import IdraPluginManager.model.PluginStatus;
import IdraPluginManager.model.PluginType;
import IdraPluginManager.pluginParamethers.model.PluginParamethersDTO;

public interface IdraPluginService {

	public Plugin addPlugin(String name, String description, String URL, PluginParamethersDTO paramethers, 
			PluginType type, PluginStatus status, PluginMethod method, List<String> compatibleFormats);
	public boolean deletePlugin(ObjectId id);
	public Plugin getPlugin(ObjectId id);
	public Plugin updatePlugin(ObjectId id, String name, String description, PluginType type,
			String link, PluginStatus statust, PluginMethod method, List<String> compatibleFormats) ;
}