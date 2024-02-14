package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@RequestMapping("/televisions") zet de basis voor de mapping annotatie.
@RequestMapping("/televisions")
@RestController
public class TelevisionsController {
    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping()
    public ResponseEntity<String> getAllTelevisions(){
        return ResponseEntity.ok("televisions");
//        return new ResponseEntity<>("televisions", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<String> getTelevision( @PathVariable("id") int id){
        return ResponseEntity.ok("televisionsId: " + id);
    }

  @PostMapping()
    public ResponseEntity<String> addTelevision(@RequestBody String television){
      return ResponseEntity.created(null).body("television");
  }

  @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable int id){
        return ResponseEntity.noContent().build();
  }

  @PutMapping("/{id}")
    public ResponseEntity<Void> updateTelevision(@PathVariable int id, @RequestBody String television) {
        return ResponseEntity.noContent().build();
  }

}
