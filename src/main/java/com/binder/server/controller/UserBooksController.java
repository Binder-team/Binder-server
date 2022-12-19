package com.binder.server.controller;

import com.binder.server.exception.ResourceNotFoundException;
import com.binder.server.model.User;
import com.binder.server.model.UserBooks;
import com.binder.server.repository.UserBooksRepository;
import com.binder.server.repository.UserRepository;
//import com.binder.server.service.URLB64Encoder;

import com.fasterxml.jackson.databind.util.NativeImageUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.coyote.Request;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.util.Base64Utils.encodeToString;

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
    public UserBooks createUserBooks(@RequestBody UserBooks userBooks, @PathVariable(value = "username")String username) throws IOException {
        User user = userRepository.findUserByUsername(username);
        userBooks.setUserId(user.getId());
        userBooks.setIsAvailable(true);
        //Intercept user book adding here and instead replace it with a image Base64 string
        String thumbImg = userBooks.getThumbnail_url();
        String bigImage = userBooks.getImage_url();

        byte[] imageBytesThumb = IOUtils.toByteArray(new URL(thumbImg));
        String base64Thumb = "data:image/jpeg;base64," + Base64.getEncoder().encodeToString(imageBytesThumb);

        byte[] imageBytesImage = IOUtils.toByteArray(new URL(bigImage));
        String base64Img = "data:image/png;base64," + Base64.getEncoder().encodeToString(imageBytesImage);

        userBooks.setThumbnail_url(base64Thumb);
        userBooks.setImage_url(base64Img);
        return this.userBooksRepository.save(userBooks);
    }

    //update UserBooks
    @PutMapping("user_books/{id}")
    public ResponseEntity<UserBooks> updateUserBooks(@PathVariable(value ="id") Long userBooksId,
           @Validated @RequestBody UserBooks userBooksDetails) {
        UserBooks userBooks = userBooksRepository.findUserBooksById(userBooksId);
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