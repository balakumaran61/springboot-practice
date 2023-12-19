package com.springbootpostpractice.crud.service;

import com.springbootpostpractice.crud.dto.GuardianDto;
import com.springbootpostpractice.crud.dto.StudentDetail;
import com.springbootpostpractice.crud.dto.guardianUpdateDto;
import com.springbootpostpractice.crud.dto.studentDto;
import com.springbootpostpractice.crud.model.Guardian;
import com.springbootpostpractice.crud.repository.Projection.GuardianProjection;
import com.springbootpostpractice.crud.repository.Projection.StudentProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface GuardianService {
    Guardian createGuardian(GuardianDto guardianDTO);
    StudentDetail getStudentDetailsByGuardianId(Integer guardianId);


     Page<GuardianProjection> getGuardianDetailPagination(Pageable pageable,String search);

 void updateGuardian( String email,guardianUpdateDto updatedGuardian);
    studentDto getStudentInfoByGuardianEmail(String guardianEmail);


}

