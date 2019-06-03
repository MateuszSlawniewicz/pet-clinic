package mat.clinic.services.map;

import mat.clinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;
    final Long ownerId = 1L;

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeServiceMap(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName("Kowalski").build());
    }

    @Test
    void findAll() {
        Set<Owner> all = ownerServiceMap.findAll();
        assertEquals(1, all.size());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void saveExistingId() {
        Long owner2Id = 2L;
        Owner owner2 = Owner.builder().id(owner2Id).build();
        Owner savedOwner = ownerServiceMap.save(owner2);
        assertEquals(owner2Id, savedOwner.getId());

    }


    @Test
    void saveNoId() {
        Owner savedOwner = ownerServiceMap.save(Owner.builder().build());
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());

    }

    @Test
    void findById() {
        Owner byId = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, byId.getId());
    }

    @Test
    void findByLastName() {
        String ownerLastName = "Kowalski";
        Owner byLastName = ownerServiceMap.findByLastName(ownerLastName);
        assertEquals(ownerLastName, byLastName.getLastName());
    }
}