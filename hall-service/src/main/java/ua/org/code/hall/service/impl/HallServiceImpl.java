package ua.org.code.hall.service.impl;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ua.org.code.hall.service.HallService;
import ua.org.code.hall.view.VO.WaiterBasicVO;
import ua.org.code.hall.view.dto.WaiterProfileDTO;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@Log4j2
public class HallServiceImpl implements HallService {

    private final RestTemplate restTemplate;

    @Autowired
    public HallServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public WaiterProfileDTO getWaiterProfileInfoByUsername(String username) {
        DayOfWeek currentDay = LocalDate.now().getDayOfWeek();

        return null;
    }
}
