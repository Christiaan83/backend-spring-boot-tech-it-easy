package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.exceptions.TelevisionNameTooLongException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
// Deze RequestMapping" op klasse niveau betekent dat elke Mapping in deze klasse begint met "localhost:8080/bonus/..."
@RequestMapping(value = "/bonus")
public class TelevisionControllerBonus {

    // De lijst is static, omdat er maar 1 lijst kan zijn.
    // De lijst is final, omdat de lijst op zich niet kan veranderen, enkel de waardes die er in staan.
    private static final List<String> televisionDatabase = new ArrayList<>();

    @GetMapping("/televisions")
    public ResponseEntity<List<String>> getAllTelevisions() {

        // Return de complete lijst met een 200 status
        return ResponseEntity.ok(televisionDatabase);

    }

    @GetMapping("/televisions/{id}")
    public ResponseEntity<String> getTelevision(@PathVariable int id) {

        // Return de waarde die op index(id) staat en een 200 status
        // Wanneer de gebruiker een request doet met id=300, maar de lijst heeft maar 3 items.
        // Dan gooit java een IndexOutOfBoundsException. De bonus methode in de ExceptionController vangt dit op en stuurt de gebruiker een berichtje.
        return ResponseEntity.ok(televisionDatabase.get(id));

    }

    @PostMapping("/televisions")
    public ResponseEntity<String> addTelevision(@RequestBody String television) {
        // Bonus bonus: check voor 20 letters:
        if(television.length()>20){
            throw new TelevisionNameTooLongException("Televisienaam is te lang");
        } else {
            // Voeg de televisie uit de parameter toe aan de lijst
            televisionDatabase.add(television);
            // Return de televisie uit de parameter met een 201 status
            return ResponseEntity.created(null).body(television);
        }

    }

    @DeleteMapping("/televisions/{id}")
    public ResponseEntity<Void> deleteTelevision(@PathVariable int id) {
        // Vervang de waarde op index(id) met null. Als we de waarde zouden verwijderen, zouden alle indexen die na deze waarden komen in de lijst met 1 afnemen.
        televisionDatabase.set(id, null);
        // Return een 204 status
        return ResponseEntity.noContent().build();

    }

    @PutMapping("/televisions/{id}")
    public ResponseEntity<Void> updateTelevision(@PathVariable int id, @RequestBody String television) {
        // In de vorige methodes hebben we impliciet gebruik gemaakt van "IndexOUtOfBoundsException" als het id groter was dan de lijst.
        // In deze methode checken we daar expliciet voor en gooien we een custom exception op.
        if(televisionDatabase.isEmpty() || id>televisionDatabase.size()){
            throw new RecordNotFoundException("Record met id: " + id + " niet gevonden in de database.");
        } else {
            // Vervang de waarde op index(id) met de television uit de parameter
            televisionDatabase.set(id, television);
            // Return een 204 status
            return ResponseEntity.noContent().build();
        }

    }


}