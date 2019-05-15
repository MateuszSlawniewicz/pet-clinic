package mat.clinic.bootstrap;

import mat.clinic.model.Owner;
import mat.clinic.model.Vet;
import mat.clinic.services.OwnerService;
import mat.clinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
        owner1.setFirstName("Adam");
        owner1.setLastName("Kowalski");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Kasia");
        owner2.setLastName("Nowak");
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
