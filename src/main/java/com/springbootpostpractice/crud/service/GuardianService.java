package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.*;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface GuardianService {
    Guardian createGuardian(GuardianDto guardianDTO);
    StudentDetail getStudentDetailsByGuardianId(Integer guardianId);


     Page<GuardianProjection> getGuardianDetailPagination(Pageable pageable,String search);

    void updateGuardian( String email,guardianUpdateDto updatedGuardian);
    StudentCourseDtoByGuardian getStudentInfoByGuardianEmail(String guardianEmail);

    ResponseEntity<String> saveGuardian(SaveGuardianDTO guardianDTO);

    GuadDto getGuardianProfile(String username);


}

