package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.StaffUserMapper;
import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import com.spaghettininjas.yaposs.repository.entity.StaffUserPasswordless;
import com.spaghettininjas.yaposs.service.StaffUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/Staff")
public class StaffUserController {

    private final StaffUsersService service;
    private final StaffUserMapper mapper;

    private final PasswordEncoder passwordEncoder;

    public StaffUserController(
        StaffUsersService service,
        StaffUserMapper mapper,
        PasswordEncoder passwordEncoder
    ) {
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StaffUserPasswordless> getById(@PathVariable int id) {
        return service.findById((long) id)
            .map(mapper::toPasswordlessDTO)
            .map(ResponseEntity.ok()::body)
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterator<StaffUserPasswordless>> findAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email
    ) {
        Iterable<StaffUser> staffUsersItarable = service
            .findAll(page, pageSize, name, email);
        var staffUsersPasswordless = StreamSupport.stream(staffUsersItarable.spliterator(), false)
            .map(mapper::toPasswordlessDTO)
            .iterator();

        return new ResponseEntity<>(staffUsersPasswordless, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<StaffUserPasswordless> createStaffUser(@RequestBody StaffUser staffUser) {
        staffUser.setId(null);
        hashPasswordOnStaffUser(staffUser);

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(
                mapper.toPasswordlessDTO(service.createOrReplace(staffUser))
            );
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<StaffUserPasswordless> createOrUpdateStaffUser(
        @PathVariable int id,
        @RequestBody StaffUser staffUser
    ) {
      staffUser.setId((long) id);
      hashPasswordOnStaffUser(staffUser);

      return service.findById((long) id)
          .map(__ -> service.createOrReplace(staffUser))
          .map(mapper::toPasswordlessDTO)
          .map(ResponseEntity.ok()::body)
          .orElseGet( () -> {
              var createdStaffUser = service.createOrReplace(staffUser);

              return ResponseEntity
                  .status(HttpStatus.CREATED)
                  .body(mapper.toPasswordlessDTO(createdStaffUser));
              }
          );
    }

    private void hashPasswordOnStaffUser(StaffUser staffUser) {
        var hashedPassword = passwordEncoder.encode(staffUser.getPasswordHash());
        staffUser.setPasswordHash(hashedPassword);
    }
}
