package guilherme.leandro.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import guilherme.leandro.inventory.model.InventoryItem;

public interface InventoryRepository extends JpaRepository<InventoryItem, String> {}
