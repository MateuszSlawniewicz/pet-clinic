package mat.clinic.repositories;

import mat.clinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findByLastNameContainingIgnoreCase(String lastName);

}
