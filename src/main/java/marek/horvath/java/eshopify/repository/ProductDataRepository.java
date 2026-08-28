package marek.horvath.java.eshopify.repository;

import marek.horvath.java.eshopify.entity.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, UUID> {
    Optional<ProductData> findByProduct_IdAndDeletedAtIsNull(UUID productId);

    @Query("SELECT pd FROM ProductData pd WHERE pd.deletedAt IS NULL ORDER BY pd.createdAt DESC")
    List<ProductData> findAllActive();
}
