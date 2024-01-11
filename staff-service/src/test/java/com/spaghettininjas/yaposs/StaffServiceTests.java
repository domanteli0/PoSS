package com.spaghettininjas.yaposs;

import com.spaghettininjas.yaposs.controller.StaffUserController;
import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import com.spaghettininjas.yaposs.service.StaffUsersService;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("tc")
@AutoConfigureMockMvc
@AutoConfigureEmbeddedDatabase
class StaffServiceTests {

	@Autowired
	private StaffUserController controller;

	@Autowired
	private StaffUsersService service;

	@Autowired
	private PasswordEncoder encoder;

	@Test
	void passwordsAreEncrypted() {
		var createdStaffUser = controller.createStaffUser(
			StaffUser.builder()
				.passwordHash("password")
				.build()
		).getBody();

		var hashedPasswordByController = service.findById(createdStaffUser.getId()).get().getPasswordHash();

		// assert that a new password encoder will be able to verify password matches
		// generated with previous password encoder
		assertThat(passwordEncoder().matches("password", hashedPasswordByController)).isTrue();
	}

	private PasswordEncoder passwordEncoder() {
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		encoders.put("bcrypt", new BCryptPasswordEncoder());

		return new DelegatingPasswordEncoder("bcrypt", encoders);
	}

}
