package com.springbootpostpractice.crud.repository.Projection;

public interface GuardianProjection {
    String getName();
    String getEmail();
    String getPhoneNo(); // Corrected the method name to match the field in Guardian class
}
