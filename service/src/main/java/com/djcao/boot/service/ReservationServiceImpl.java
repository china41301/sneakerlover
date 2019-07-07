package com.djcao.boot.service;

import com.djcao.boot.repository.ReservationRegistrationRepository;
import com.djcao.boot.repository.ReservationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {
    @Autowired
    private ReservationRegistrationRepository reservationRegistrationRepository;

    @Autowired
    private ReservationUserRepository reservationUserRepository;
}
