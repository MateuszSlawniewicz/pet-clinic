package mat.clinic.fromatters;

import mat.clinic.model.PetType;
import mat.clinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatter implements Formatter<PetType> {
    private final PetTypeService petTypeService;

    public PetTypeFormatter(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }


    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> petTypes = petTypeService.findAll();
        for (PetType petType : petTypes) {
            if (petType.getName().equals(text)) {
                return petType;
            }
        }
        throw new ParseException("type not found: " + text, 0);

    }

    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }
}
