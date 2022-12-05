package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.UserBooks;
import com.binder.server.repository.UserBooksRepository;
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

    public UserBooksController(UserBooksRepository userBooksRepository) {
        this.userBooksRepository = userBooksRepository;
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
    @PostMapping("user_books")
    public UserBooks createUserBooks(@RequestBody UserBooks userBooks) {
        return this.userBooksRepository.save(userBooks);
    }

    //update UserBooks
    @PutMapping("user_books/{id}")
    public ResponseEntity<UserBooks> updateUserBooks(@PathVariable(value ="id") Long userBooksId,
           @Validated @RequestBody UserBooks userBooksDetails) throws ResourceNotFoundException {
        UserBooks userBooks = userBooksRepository.findById(userBooksId)
                .orElseThrow(() -> new ResourceNotFoundException("UserBooks not found for this id :: " +userBooksId));
        userBooks.setUser_id(userBooksDetails.getUser_id());
        userBooks.setBook_id(userBooks.getBook_id());
        userBooks.setIs_available(userBooks.isIs_available());
        userBooks.setIsbn(userBooks.getIsbn());
        userBooks.setCondition(userBooks.getCondition());

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


}
