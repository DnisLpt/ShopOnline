package shoponline.repository;

import shoponline.models.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UzerRepository extends JpaRepository<Uzer, Long>{
    public Uzer findByUsername(String username);
    public Uzer findById(long id);
}