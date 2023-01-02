package com.sudhir.webfluxexample.service;

import com.sudhir.webfluxexample.dto.UserResponse;
import com.sudhir.webfluxexample.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final WebClient webClient;

    public List<UserResponse> getAllUsers() {
       UserResponse[] userResponses =  webClient.get().uri("https://gorest.co.in/public/v2/users")
                .accept(MediaType.APPLICATION_JSON)
                .header("Authorization" , "Bearer 0be3d3656a14c07af4c96b06ffc9d8cfbe18933240f24bf609161aecef43cefc")
                .retrieve()
                .bodyToMono(UserResponse[].class)
                .block();

       log.info(Arrays.toString(userResponses));

       List<UserResponse> userResponses1 = Arrays.stream(userResponses).collect(Collectors.toList());

        return userResponses1;
    }

    public UserResponse createUser(User user) {

        log.info(user.toString());



            UserResponse userResponse =   webClient.post().uri("https://gorest.co.in/public/v2/users" )

                    .header("Authorization" , "Bearer 0be3d3656a14c07af4c96b06ffc9d8cfbe18933240f24bf609161aecef43cefc")
                    .body(Mono.just(user),User.class)
                    .retrieve()
                    .bodyToMono(UserResponse.class)
                    .block();



        return userResponse;
    }

    public UserResponse updateUser(Long id, User user) {

       UserResponse userResponse =  webClient.put().uri("https://gorest.co.in/public/v2/users" + "/" + id)
                .header("Authorization" , "Bearer 0be3d3656a14c07af4c96b06ffc9d8cfbe18933240f24bf609161aecef43cefc")
                .body(Mono.just(user) , User.class)
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();

        return userResponse;
    }

    public String deleteUser(Long id) {

        UserResponse userResponse =  webClient.delete().uri("https://gorest.co.in/public/v2/users" + "/" + id)
                .header("Authorization" , "Bearer 0be3d3656a14c07af4c96b06ffc9d8cfbe18933240f24bf609161aecef43cefc")
                .retrieve()
                .bodyToMono(UserResponse.class)
                .block();

        return "User Deleted";



    }
}
