package jp.tcmobile.bamboo.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import jp.tcmobile.bamboo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
