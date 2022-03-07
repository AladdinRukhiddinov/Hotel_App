package uz.pdp.spring_10_1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "rooms")
@Table(uniqueConstraints =
@UniqueConstraint(name = "UniqueRoomNumberAndRoomHotel", columnNames = {"number", "hotel_id"}))
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "number")
    private Integer number;

    @Column(nullable = false)
    private Integer floor;

    @Column(nullable = false)
    private String size;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;
}
