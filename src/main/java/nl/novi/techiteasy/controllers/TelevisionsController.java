package nl.novi.techiteasy.controllers;

import nl.novi.techiteasy.exceptions.RecordNotFoundException;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@RequestMapping("/televisions") zet de basis voor de mapping annotatie.
@RequestMapping("/televisions")
@RestController
public class TelevisionsController {
    private final TelevisionRepository televisionRepository;

    public TelevisionsController(TelevisionRepository televisionRepository) {
        this.televisionRepository = televisionRepository;
    }

    @GetMapping()
    public ResponseEntity<List<Television>> getAllTelevisions(@RequestParam(value = "brand", required = false) String brand) {

        List<Television> televisions;
        if (brand == null) {
            televisions = televisionRepository.findAll();
        } else {
            televisions = televisionRepository.findAllTelevisionsByBrandEqualsIgnoreCase(brand);
        }
        return ResponseEntity.ok().body(televisions);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Television> getTelevision(@PathVariable("id") Long id) {

        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {

            throw new RecordNotFoundException("No television found with id: " + id);

        } else {
            Television television1 = television.get();

            return ResponseEntity.ok().body(television1);
        }

    }


    @PostMapping()
    public ResponseEntity<Television> addTelevision(@RequestBody Television television) {

        Television returnTelevision = televisionRepository.save(television);

        return ResponseEntity.created(null).body(returnTelevision);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteTelevision(@PathVariable("id") Long id) {
        televisionRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Television> updateTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);
        if (television.isEmpty()){

            throw new RecordNotFoundException("No television found with id: " + id);

        }else {
            // Verander alle waardes van de television uit de database naar de waardes van de television uit de parameters.
            // Behalve de id. Als je de id veranderd, dan wordt er een nieuw object gemaakt in de database.
            Television television1 = television.get();

            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setAvailableSize(newTelevision.getAvailableSize());
            television1.setAmbiLight(newTelevision.getAmbiLight());
            television1.setBluetooth(newTelevision.getBluetooth());
            television1.setBrand(newTelevision.getBrand());
            television1.setHdr(newTelevision.getHdr());
            television1.setName(newTelevision.getName());
            television1.setOriginalStock(newTelevision.getOriginalStock());
            television1.setPrice(newTelevision.getPrice());
            television1.setRefreshRate(newTelevision.getRefreshRate());
            television1.setScreenQuality(newTelevision.getScreenQuality());
            television1.setScreenType(newTelevision.getScreenType());
            television1.setSmartTv(newTelevision.getSmartTv());
            television1.setSold(newTelevision.getSold());
            television1.setType(newTelevision.getType());
            television1.setVoiceControl(newTelevision.getVoiceControl());
            television1.setWifi(newTelevision.getWifi());
            // Sla de gewijzigde waarden op in de database onder dezelfde id. Dit moet je niet vergeten.
            Television returnTelevision = televisionRepository.save(television1);
            // Return de nieuwe versie van deze tv en een 200 code
            return ResponseEntity.ok().body(returnTelevision);
        }

    }

    @PatchMapping("/{id}")
    public ResponseEntity<Television> updatePartialTelevision(@PathVariable Long id, @RequestBody Television newTelevision) {
        Optional<Television> television = televisionRepository.findById(id);

        if (television.isEmpty()) {
            throw new RecordNotFoundException("No television found with id: " + id);
        } else {

            Television television1 = television.get();
            if (newTelevision.getAmbiLight() != null) {
                television1.setAmbiLight(newTelevision.getAmbiLight());
            }
            if (newTelevision.getAvailableSize() != null) {
                television1.setAvailableSize(newTelevision.getAvailableSize());
            }
            if (newTelevision.getBluetooth()) {
                television1.setBluetooth(newTelevision.getBluetooth());
            }
            if (newTelevision.getBrand() != null) {
                television1.setBrand(newTelevision.getBrand());
            }
            if (newTelevision.getHdr() != null) {
                television1.setHdr(newTelevision.getHdr());
            }
            if (newTelevision.getName() != null) {
                television1.setName(newTelevision.getName());
            }
            if (newTelevision.getOriginalStock() != null) {
                television1.setOriginalStock(newTelevision.getOriginalStock());
            }
            if (newTelevision.getPrice() != null) {
                television1.setPrice(newTelevision.getPrice());
            }
            if (newTelevision.getRefreshRate() != null) {
                television1.setRefreshRate(newTelevision.getRefreshRate());
            }
            if (newTelevision.getScreenQuality() != null) {
                television1.setScreenQuality(newTelevision.getScreenQuality());
            }
            if (newTelevision.getScreenType() != null) {
                television1.setScreenType(newTelevision.getScreenType());
            }
            if (newTelevision.getSmartTv() != null) {
                television1.setSmartTv(newTelevision.getSmartTv());
            }
            if (newTelevision.getSold() != null) {
                television1.setSold(newTelevision.getSold());
            }
            if (newTelevision.getType() != null) {
                television1.setType(newTelevision.getType());
            }
            if (newTelevision.getVoiceControl() != null) {
                television1.setVoiceControl(newTelevision.getVoiceControl());
            }
            if (newTelevision.getWifi() != null) {
                television1.setWifi(newTelevision.getWifi());
            }

            Television returnTelevision = televisionRepository.save(television1);
            return ResponseEntity.ok().body(returnTelevision);
        }
    }

}
