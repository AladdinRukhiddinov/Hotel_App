package uz.pdp.spring_10_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring_10_1.entity.Hotel;
import uz.pdp.spring_10_1.repository.HotelRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hotel")
public class HotelController {

    private final HotelRepository hotelRepository;

    @GetMapping
    public Page<Hotel> getAllHotels(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 5);
        return hotelRepository.findAll(pageable);
    }

    @GetMapping("/{hotelId}")
    public Hotel getHotelById(@PathVariable Long hotelId) {
        return hotelRepository.findById(hotelId).orElse(null);
    }

    @PostMapping
    public String addHotel(@RequestBody Hotel hotel){
        try {
        hotelRepository.save(hotel);
        return "Successfully added!!!";
        } catch (Exception e){
            return "Error! Maybe hotel already exists!";
        }
    }

    @PutMapping("/{hotelId}")
    public String editHotelById(@PathVariable Long hotelId, @RequestBody Hotel hotel){
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            return "Hotel not found!!!";
        }
        Hotel editingHotel = optionalHotel.get();
        editingHotel.setName(hotel.getName());
        try {
        hotelRepository.save(editingHotel);
            return "Successfully edited!!";
        }catch (Exception e){
            return "Error! Maybe hotel already exists!";
        }
    }

    @DeleteMapping("/{hotelId}")
    public String deleteHotelById(@PathVariable Long hotelId){
        try {
            hotelRepository.deleteById(hotelId);
            return "Successfully deleted!!!";
        } catch (Exception e){
            return "Error! Maybe hotel not found!";
        }
    }
}
