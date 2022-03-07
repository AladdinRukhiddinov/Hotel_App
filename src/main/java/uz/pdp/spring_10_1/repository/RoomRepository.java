package uz.pdp.spring_10_1.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring_10_1.entity.Hotel;
import uz.pdp.spring_10_1.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {
    boolean existsByNumberAndHotelAndIdNot(Integer number, Hotel hotel, Long id);
    Page<Room> findAllByHotel(Hotel hotel, Pageable pageable);
}
