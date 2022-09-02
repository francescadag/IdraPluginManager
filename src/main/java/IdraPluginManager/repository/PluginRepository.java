package IdraPluginManager.repository;

import java.util.List;
import java.util.Optional;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import IdraPluginManager.model.Plugin;

public interface PluginRepository extends MongoRepository<Plugin, ObjectId> {
	
	@Query("{ 'id' : ?#{ principal.name } }")
	Optional<Plugin>readByPrincipal();
	
	@Query("{ '_id' : ?0, 'id' : ?#{ principal.name } }")
	Optional<Plugin> readByPrincipalAndId(ObjectId id);
	
	Optional<Plugin> findById(ObjectId id);
	
	Optional<Plugin> findByName(String name);
	
	List<Plugin> findAll();
	
}
