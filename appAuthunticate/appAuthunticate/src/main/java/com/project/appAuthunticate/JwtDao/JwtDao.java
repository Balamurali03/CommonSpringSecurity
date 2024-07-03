package com.project.appAuthunticate.JwtDao;

import java.util.Optional;
//import java.util.Timer;
//import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.appAuthunticate.JwtEntity.JwtEntity;
import com.project.appAuthunticate.JwtRepo.JwtRepo;
import com.project.appAuthunticate.JwtService.UserService;
import com.project.appAuthunticate.JwtUtil.JwtUtility;

@Service
public class JwtDao {
	@Autowired
	private JwtRepo repo;

	@Autowired
	private JwtUtility jwtUtility;

	@Autowired
	private UserService userService;

	public String generateJwtToken(JwtEntity entity) {
		Optional<JwtEntity> updateEntity = repo.findByEmail(entity.getEmail());
		if (updateEntity.isEmpty()) {
			repo.save(entity);
			//new JwtDao().deleteData(repo, entity);
			return jwtUtility.generateToken(userService.loadUserByUsername(entity.getEmail()));
		} else {
			repo.save(updateEntity.get());
			//new JwtDao().deleteData(repo, updateEntity.get());
			return jwtUtility.generateToken(userService.loadUserByUsername(updateEntity.get().getEmail()));
		}

	}

//	public void deleteData(JwtRepo repo, JwtEntity entity) {
//
//		Timer timer = new Timer();
//
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//
//				repo.delete(entity);
//			}
//		};
//
//		timer.schedule(task, 1000 * 60 * 30);
//
//	}
}
