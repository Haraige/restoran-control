package ua.org.code.hall.service;

import ua.org.code.hall.view.dto.WaiterProfileDTO;

public interface HallService {

    WaiterProfileDTO getWaiterProfileInfoByUsername(String username);

}
