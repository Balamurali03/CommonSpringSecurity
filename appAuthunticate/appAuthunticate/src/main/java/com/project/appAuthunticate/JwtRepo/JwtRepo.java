package com.project.appAuthunticate.JwtRepo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.appAuthunticate.JwtEntity.JwtEntity;

public interface JwtRepo extends JpaRepository<JwtEntity,Long>{

	public Optional<JwtEntity> findByEmail(String email);
}
