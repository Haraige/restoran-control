package ua.org.code.hall.service;

import ua.org.code.hall.view.VO.WaiterBasicVO;
import ua.org.code.hall.view.dto.WaiterProfileDTO;

import java.util.List;

public interface HallService {

    WaiterProfileDTO getWaiterProfileInfoByUsername(String username);

}
