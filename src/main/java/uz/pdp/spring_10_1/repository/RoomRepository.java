package uz.pdp.spring_10_1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.spring_10_1.entity.Room;

public interface RoomRepository extends JpaRepository<Room, Long> {

}
