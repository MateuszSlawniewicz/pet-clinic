package mat.clinic.services;

import mat.clinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);


}
