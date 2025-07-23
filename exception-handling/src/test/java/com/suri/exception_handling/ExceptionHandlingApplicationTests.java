package com.suri.exception_handling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.suri.exception_handling.model.User;
import com.suri.exception_handling.repo.UserRepo;
import com.suri.exception_handling.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ExceptionHandlingApplicationTests {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepo userRepo;

    @Test
    public void getUsersTest() {
        when(userRepo.findAll()).thenReturn(Stream
                .of(new User(100,"sreevalli","srivalli@email.com","hyd","female"),
                    new User(101,"pushpa","pushpa@email.com","tirupati","male"))
                .collect(Collectors.toList()));

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
    }

    @Test
    public void getUserById() {
        long id = 100;

        List<User> usersList = List.of(
                new User(100,"sreevalli","srivalli@email.com","hyd","female"),
                new User(101,"pushpa","pushpa@email.com","tirupati","male")
        );

        // Verify user exists in mock data
        boolean userExists = usersList.stream().anyMatch(user -> user.getId() == id);
        assertTrue(userExists, "User with ID " + id + " should exist");

        // Mock repo
        when(userRepo.findById(id)).thenReturn(Optional.of(usersList.get(0)));

        // Call service
        User result = userService.getUser(id);

        // Assertions
        assertNotNull(result);
        assertEquals("sreevalli", result.getName());
    }
}
