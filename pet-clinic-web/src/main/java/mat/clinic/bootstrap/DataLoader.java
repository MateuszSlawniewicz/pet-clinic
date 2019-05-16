package mat.clinic.bootstrap;

import mat.clinic.model.*;
import mat.clinic.services.OwnerService;
import mat.clinic.services.PetTypeService;
import mat.clinic.services.SpecialityService;
import mat.clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
        int counter = petTypeService.findAll().size();
        if (counter == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType savedDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType savedCat = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality savedRadiology = specialityService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality savedSurgery = specialityService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality savedDentistry = specialityService.save(dentistry);

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
        vet1.getSpecialities().add(savedRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstName("Anna");
        vet2.setLastName("Lis");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
    }
}
