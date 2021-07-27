package ua.org.code.personneldepartment.constants;

import java.util.Map;

public class KeycloakRolesConstants {
    public static final Map<String, String> WAITER_ROLE_REPRESENTATION = Map.of(
            "id","27b50a35-fc59-4b01-95ef-4decae8371d6",
            "name", "WAITER",
            "composite", "true",
            "clientRole", "false",
            "containerId", "Restaurant");

    public static final Map<String, String> COOKER_ROLE_REPRESENTATION = Map.of(
            "id", "0ba63d85-f8c9-47bd-9840-e2f8388f2a7d",
            "name", "COOKER",
            "composite", "true",
            "clientRole", "false",
            "containerId", "Restaurant  "
    );

}
