package marek.horvath.java.eshopify.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "product_data_id", nullable = false)
    private UUID productDataId;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getProductDataId() {
        return productDataId;
    }

    public void setProductDataId(UUID productDataId) {
        this.productDataId = productDataId;
    }
}
