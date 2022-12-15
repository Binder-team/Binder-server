package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.User;
import com.binder.server.model.UserBooks;
import com.binder.server.repository.UserBooksRepository;
import com.binder.server.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class UserBooksController {

    private final UserBooksRepository userBooksRepository;
    private final UserRepository userRepository;

    public UserBooksController(UserBooksRepository userBooksRepository, UserRepository userRepository) {
        this.userBooksRepository = userBooksRepository;
        this.userRepository = userRepository;
    }

    //get userBooks
    @GetMapping("user_books")
    public List<UserBooks> getAllUserBooks(){
        return this.userBooksRepository.findAll();
    }

    //get userBooks by id
    @GetMapping("user_books/{id}")
    public ResponseEntity<UserBooks> getUserBooksById(@PathVariable(value = "id") Long userBooksId)
        throws ResourceNotFoundException {
        UserBooks userBooks = userBooksRepository.findById(userBooksId)
                .orElseThrow(() -> new ResourceNotFoundException("UserBooks not found for this id :: " + userBooksId));
        return ResponseEntity.ok().body(userBooks);
    }

    //save UserBooks
    @PostMapping("user_books/user/{username}")
    public UserBooks createUserBooks(@RequestBody UserBooks userBooks, @PathVariable(value = "username")String username) {
        User user = userRepository.findUserByUsername(username);
        userBooks.setUserId(user.getId());
        userBooks.setIsAvailable(true);
        return this.userBooksRepository.save(userBooks);
    }

    //update UserBooks
    @PutMapping("user_books/{id}")
    public ResponseEntity<UserBooks> updateUserBooks(@PathVariable(value ="id") Long userBooksId,
           @Validated @RequestBody UserBooks userBooksDetails) throws ResourceNotFoundException {
        UserBooks userBooks = userBooksRepository.findById(userBooksId)
                .orElseThrow(() -> new ResourceNotFoundException("UserBooks not found for this id :: " +userBooksId));
        userBooks.setUserId(userBooksDetails.getUserId());
        userBooks.setBook_id(userBooks.getBook_id());
        userBooks.setIsAvailable(userBooks.isIsAvailable());
        userBooks.setIsbn(userBooks.getIsbn());
        userBooks.setCondition(userBooks.getCondition());
        userBooks.setImage_url(userBooks.getImage_url());
        userBooks.setThumbnail_url(userBooks.getThumbnail_url());
        userBooks.setTitle(userBooks.getTitle());
        userBooks.setAuthor(userBooks.getAuthor());

        return ResponseEntity.ok(this.userBooksRepository.save(userBooks));

    }
    //delete userBooks
    @DeleteMapping("user_books/{id}")
    public Map<String, Boolean> deleteUserBooks(@PathVariable(value ="id") Long userBooksId
    ) throws ResourceNotFoundException {
        UserBooks userBooks = userBooksRepository.findById(userBooksId)
                .orElseThrow(() -> new ResourceNotFoundException("UserBooks not found for this id :: " +userBooksId));
        this.userBooksRepository.delete(userBooks);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

    @GetMapping("user_books/user/{username}")
    public List<UserBooks> findUserBooks(@PathVariable(value = "username") String username){
        User user = userRepository.findUserByUsername(username);
        return this.userBooksRepository.findByUserId(user.getId());
    }

    @GetMapping("user_books/swipe/{username}")
    public List<UserBooks> findOtherBooks(@PathVariable(value = "username") String username) {
        User user = userRepository.findUserByUsername(username);
        return this.userBooksRepository.findByUserIdNotAndIsAvailable(user.getId(), true);
    }
}