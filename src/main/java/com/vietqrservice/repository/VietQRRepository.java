package com.vietqrservice.repository;

import com.vietqrservice.entity.VietQR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VietQRRepository extends JpaRepository<VietQR, Long> {
}
