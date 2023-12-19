package com.springbootpostpractice.crud.repository.Projection;

import lombok.Data;


public interface StudentPageProjection
{
    String getName();
    String getEmail();
    int getAge();

    String getRollno();
}
