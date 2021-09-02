package com.sital.multipleauthentication.repositories;

import com.sital.multipleauthentication.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpRepository extends JpaRepository<Otp,Integer> {
}
