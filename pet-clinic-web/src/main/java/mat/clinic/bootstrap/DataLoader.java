package mat.clinic.bootstrap;

import mat.clinic.model.Owner;
import mat.clinic.model.Pet;
import mat.clinic.model.PetType;
import mat.clinic.model.Vet;
import mat.clinic.services.OwnerService;
import mat.clinic.services.PetTypeService;
import mat.clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("Adam");
        owner1.setLastName("Kowalski");
        owner1.setAddress("123 Main");
        owner1.setCity("Warsaw");
        owner1.setTelephone("123456798");
        Pet firstPet = new Pet();
        firstPet.setPetType(dog);
        firstPet.setBirthDate(LocalDate.of(2010, 12, 20));
        firstPet.setName("Bucky");
        firstPet.setOwner(owner1);
        owner1.getPets().add(firstPet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kasia");
        owner2.setLastName("Nowak");
        owner2.setAddress("123 Main");
        owner2.setCity("Warsaw");
        owner2.setTelephone("123456798");
        Pet secondPet = new Pet();
        secondPet.setName("Tom");
        secondPet.setBirthDate(LocalDate.of(2012, 10, 5));
        secondPet.setPetType(cat);
        secondPet.setOwner(owner2);
        owner2.getPets().add(secondPet);

        ownerService.save(owner2);

        Vet vet1 = new Vet();
        vet1.setFirstName("Marcin");
        vet1.setLastName("Pietrzak");
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anna");
        vet2.setLastName("Lis");
        vetService.save(vet2);

    }
}
