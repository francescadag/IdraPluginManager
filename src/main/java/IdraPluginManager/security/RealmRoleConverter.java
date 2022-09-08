package IdraPluginManager.security;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class RealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {

	@SuppressWarnings("unchecked")
	@Override
	public Collection<GrantedAuthority> convert(Jwt jwt) {
		if (jwt.hasClaim("clientId")) {
			return Arrays.asList(new SimpleGrantedAuthority("ROLE_CLIENT"));
		} else {
			Map<String, Object> claims = jwt.getClaimAsMap("realm_access");
			List<String> rolesS = (ArrayList<String>) claims.getOrDefault("roles", Arrays.asList());

			return rolesS.stream().map(r -> {
				if (!r.startsWith("_"))
					return new SimpleGrantedAuthority("ROLE_" + r.toUpperCase());
				else
					return new SimpleGrantedAuthority("ROLE" + r.toUpperCase());
			}).collect(Collectors.toList());
		}
	}
}