package com.spaghettininjas.yaposs.controller;

import com.spaghettininjas.yaposs.repository.StaffUserMapper;
import com.spaghettininjas.yaposs.repository.entity.StaffUser;
import com.spaghettininjas.yaposs.repository.entity.StaffUserDTO;
import com.spaghettininjas.yaposs.service.StaffUsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/Staff")
public class StaffUserController {

    private final StaffUsersService service;
    private final StaffUserMapper mapper;

    public StaffUserController(StaffUsersService service, StaffUserMapper mapper) {
        this.mapper = mapper;
        this.service = service;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StaffUser> getById(@PathVariable int id) {
        return service.findById((long) id)
                .map(staff -> ResponseEntity.ok().body(staff))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<Iterable<StaffUser>> findAll(
        @RequestParam(required = false, defaultValue = "0") Integer page,
        @RequestParam(required = false, defaultValue = "10") Integer pageSize,
        @RequestParam(required = false) String name,
        @RequestParam(required = false) String email
    ) {
        Iterable<StaffUser> staffUsers = service.findAll(page, pageSize, name, email);
        return new ResponseEntity<>(staffUsers, HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable int id){
        service.deleteById((long) id);
    }

    @PostMapping
    public ResponseEntity<StaffUser> addStaffUser(@RequestBody StaffUser staffUser) {
        // TODO: password hashing
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(staffUser));
    }

    @PutMapping(path = "/{id}")
    ResponseEntity<StaffUser> addOrUpdateStaffUser(
        @PathVariable int id,
        @RequestBody StaffUserDTO dto
    ) {
      // TODO: password hashing
      dto.setId((long) id);
      return service.findById((long) id)
            .map(value -> mapper.mergeWithDto(value, dto))
            .map(service::save)
            .map(value -> ResponseEntity.ok().body(value))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
