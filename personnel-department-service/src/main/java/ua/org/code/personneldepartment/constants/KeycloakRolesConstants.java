package ua.org.code.personneldepartment.constants;

import java.util.Map;

public class KeycloakRolesConstants {
    public static final Map<String, String> WAITER_ROLE_REPRESENTATION = Map.of(
            "id","44ffd8c9-aeff-4dbd-860f-181785dac1de",
            "name", "WAITER",
            "composite", "true",
            "clientRole", "false",
            "containerId", "Restaurant");

    public static final Map<String, String> COOKER_ROLE_REPRESENTATION = Map.of(
            "id", "3d0ace95-ac97-43f0-9256-61ccac28829b",
            "name", "COOKER",
            "composite", "true",
            "clientRole", "false",
            "containerId", "Restaurant"
    );

}
