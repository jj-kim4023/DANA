package com.example.dana.seller.domain.repository;

import com.example.dana.seller.domain.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findItemById(Long id);
}
