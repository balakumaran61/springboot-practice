package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.GuardianDto;
import com.springbootpostpractice.crud.model.Guardian;
import org.springframework.stereotype.Service;

@Service
public interface GuardianService {
    Guardian createGuardian(GuardianDto guardianDTO);

}
