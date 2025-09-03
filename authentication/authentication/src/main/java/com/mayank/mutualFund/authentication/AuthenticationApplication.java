package com.mayank.mutualFund.authentication;

import com.mayank.mutualFund.authentication.entity.User;
import com.mayank.mutualFund.authentication.enumClasses.Role;
import com.mayank.mutualFund.authentication.repository.UserRepository;
import com.mayank.mutualFund.authentication.service.OtpService;
import com.mayank.mutualFund.authentication.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AuthenticationApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthenticationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(UserService userService, OtpService otpService){
		return args -> {
			User user=User.builder()
					.email("mayanknetam.work@gmail.com")
					.username("monu")
					.password("admin")
					.role(Role.ADMIN)
					.build();
			User user1=userService.saveUser(user);
			otpService.sendOtp(user1.getEmail(),user1.getVerificationCode());
			System.out.println("Admin Created "+user1.toString());

		};
	}
}
