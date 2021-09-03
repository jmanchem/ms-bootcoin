package bootcoin.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;

@Data
@Builder
@Document("Bootcoin")
@AllArgsConstructor
@NoArgsConstructor
public class Bootcoin {
    @Id
    String id;
    @Valid
    Double tasaCompra;
    @Valid
    Double tasaVenta;
    @Valid
    String moneda;
}
