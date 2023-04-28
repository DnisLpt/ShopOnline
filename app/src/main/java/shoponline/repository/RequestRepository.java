package shoponline.repository;

import shoponline.models.Request;
import shoponline.models.Uzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long> {
    public Request findById(long id);
    public List<Request> findByUser(Uzer uzer);
    public Request findByUserAndConfirmed(Uzer uzer, boolean confirmed);
}
