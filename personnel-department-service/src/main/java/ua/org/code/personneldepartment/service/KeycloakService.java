package ua.org.code.personneldepartment.service;

import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
public class KeycloakService {

    @Value("${keycloak.uri}")
    private String keycloakUri;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    private final RestTemplate restTemplate = new RestTemplate();

    @Transactional
    public void createUserWithRole(String firstName,
                             String lastName,
                             String email,
                             String username,
                             String password,
                             Map<String, String> roleRepresentation) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        Map<String, String> createUserRequestMap = Map.of(
                "firstName", firstName,
                "lastName", lastName,
                "username", username,
                "email", email,
                "enabled", "true"
        );

        restTemplate.postForEntity(
                keycloakUri + "/admin/realms/" + keycloakRealm + "/users",
                new HttpEntity<>(createUserRequestMap, headers),
                String.class);

        addRoleToUser(getUserIdByUsername(username), roleRepresentation);
        setUserPassword(getUserIdByUsername(username), password);
    }

    public void updateUserInfo(UUID userId,
                               String firstName,
                               String lastName,
                               String email,
                               String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        Map<String, String> updateUserRequestMap = Map.of(
                "firstName", firstName,
                "lastName", lastName,
                "username", username,
                "email", email
        );

        restTemplate.put(keycloakUri + "/admin/realms/" + keycloakRealm + "/users/" + userId,
                new HttpEntity<>(updateUserRequestMap, headers));
    }

    public void deleteUser(UUID userId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        restTemplate.exchange(keycloakUri + "/admin/realms/" + keycloakRealm + "/users/" + userId,
                HttpMethod.DELETE,
                new HttpEntity<>(null, headers),
                Void.class);
    }

    public void setUserPassword(UUID userId, String newPassword) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        Map<String, String> credentialsRepresentation = Map.of(
                "value", newPassword,
                "temporary", "false"
        );

        restTemplate.put(
                keycloakUri + "/admin/realms/" + keycloakRealm + "/users/" + userId + "/reset-password",
                new HttpEntity<>(credentialsRepresentation, headers)
        );

    }

    private void addRoleToUser(UUID userId, Map<String, String> roleRepresentation) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        List<Map<String, String>> userAddedRolesList = List.of(roleRepresentation);
        restTemplate.postForEntity(
                keycloakUri + "/admin/realms/" + keycloakRealm + "/users/" + userId + "/role-mappings/realm",
                new HttpEntity<>(userAddedRolesList, headers),
                String.class);
    }



    public UUID getUserIdByUsername(String username) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getAdminBearerToken());

        UserRepresentation userRepresentation = Objects.requireNonNull(restTemplate.exchange(
                keycloakUri + "/admin/realms/" + keycloakRealm + "/users?username=" + username + "&max=1",
                HttpMethod.GET,
                new HttpEntity<>(null, headers),
                UserRepresentation[].class).getBody())[0];

        return UUID.fromString(userRepresentation.getId());
    }

    private String getAdminBearerToken() {
        Jwt jwt = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return jwt.getTokenValue();
    }

}
