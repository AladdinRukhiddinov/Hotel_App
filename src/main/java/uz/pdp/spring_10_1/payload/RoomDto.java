package uz.pdp.spring_10_1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class RoomDto {

    private Integer number;

    private Integer floor;

    private String size;
}
