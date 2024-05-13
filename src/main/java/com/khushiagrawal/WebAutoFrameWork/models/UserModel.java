package com.khushiagrawal.WebAutoFrameWork.models;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

        private String first_name;
        private String last_name;
        private String emailID;
        private String password;

        public UserModel init() {
            Faker faker = new Faker();
            return UserModel.builder()
                    .first_name(faker.name().firstName())
                    .last_name(faker.name().lastName())
                    .emailID(faker.internet().emailAddress())
                    .password("Pass123@")
                    .build();
        }

        public UserModel userWithoutEmail() {
            UserModel user = this.init();
            user.setEmailID("");
            return user;
        }

        public UserModel userWithoutPassword() {
            UserModel user = this.init();
            user.setPassword("");
            return user;
        }

        public UserModel userWithValidCredentials() {
            return UserModel.builder()
                    .first_name("Khushi")
                    .last_name("Agrawal")
                    .emailID("khushiagrawl0302@gmail.com")
                    .password("khushi123")
                    .build();
        }

        public UserModel userWithInvalidCredentials() {
            return UserModel.builder()
                    .first_name("Khushi")
                    .last_name("Agrawal")
                    .emailID("khushiagrawal0302@gmail.com")
                    .password("khushi@123")
                    .build();
        }

}
