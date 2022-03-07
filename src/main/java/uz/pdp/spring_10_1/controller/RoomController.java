package uz.pdp.spring_10_1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.spring_10_1.entity.Hotel;
import uz.pdp.spring_10_1.entity.Room;
import uz.pdp.spring_10_1.payload.RoomDto;
import uz.pdp.spring_10_1.repository.HotelRepository;
import uz.pdp.spring_10_1.repository.RoomRepository;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/room")
public class RoomController {

    private final RoomRepository roomRepository;
    private final HotelRepository hotelRepository;

    @GetMapping("/forHotel/{hotelId}")
    public Page<Room> getAllRooms(@PathVariable Long hotelId,@RequestParam Integer page) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            return null;
        }
        Pageable pageable = PageRequest.of(page, 5);
        return roomRepository.findAllByHotel(optionalHotel.get(),pageable);
    }

    @GetMapping("/{roomId}")
    public Room getRoomById(@PathVariable Long roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    @PostMapping("/{hotelId}")
    public String addRoom(@PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        Optional<Hotel> optionalHotel = hotelRepository.findById(hotelId);
        if (optionalHotel.isEmpty()) {
            return "Hotel not found!";
        }
        Hotel hotel = optionalHotel.get();
        Room room = new Room();
        room.setFloor(roomDto.getFloor());
        room.setHotel(hotel);
        room.setSize(roomDto.getSize());
        room.setNumber(roomDto.getNumber());
        try {
            roomRepository.save(room);
            return "Room successfully added!";
        } catch (Exception e) {
            return "Error! Maybe room already exists!!";
        }

    }

    @PutMapping("/{roomId}/{hotelId}")
    public String editRoomById(@PathVariable Long roomId, @PathVariable Long hotelId, @RequestBody RoomDto roomDto) {
        Optional<Room> optionalRoom = roomRepository.findById(roomId);
        if (optionalRoom.isEmpty()) {
            return "Room not found!!";
        }
        if (hotelRepository.findById(hotelId).isEmpty()) {
            return "Hotel not found!!";
        }
        Room editRoom = optionalRoom.get();
        boolean exists = roomRepository.existsByNumberAndHotelAndIdNot(roomDto.getNumber(), hotelRepository.getById(hotelId), roomId);
        if (exists) {
            return "Error! Maybe room already exists!!";
        }
        editRoom.setNumber(roomDto.getNumber());
        editRoom.setFloor(roomDto.getFloor());
        editRoom.setSize(roomDto.getSize());
        editRoom.setHotel(hotelRepository.getById(hotelId));
        roomRepository.save(editRoom);
        return "Successfully edited!!";
    }

    @DeleteMapping("/{roomId}")
    public String deleteRoom(@PathVariable Long roomId){
        try {
            roomRepository.deleteById(roomId);
            return "Successfully deleted!";
        } catch (Exception e){
            return "Not delete!!";
        }
    }

}
