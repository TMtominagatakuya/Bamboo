package jp.tcmobile.bamboo.repository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import jp.tcmobile.bamboo.model.Test;

@Repository
public interface TestRepository extends JpaRepository<Test, Integer> {
    public List<Test> findAll();
    public List<Test> findByCategoryId(int category_id);
    
    public Page<Test> findAll(Pageable pageable);
    public Page<Test> findByCategoryId(int category_id, Pageable pageable);
    public Page<Test> findByIsDeleted(byte isDeleated,Pageable pageable);
}