package services;

import model.Person;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class PersonDataRemoveFileService {

    //---------------------------------------------------------

    private final PersonContactService personContactService = new PersonContactFileService();

    //-------------------------------------------------------------

    public void deletePersonById(long personId) {
        List<Person> afterDeletion = personContactService.readPersonsFromFile()
                .stream()
                .filter(person -> person.getId() != personId)
                .collect(Collectors.toList());
        clearFile();
        afterDeletion.forEach(personContactService::writeToFile);
    }

    //-----------------------------------------------------------------

    private void clearFile() {
        File file = new File(personContactService.getFilePath());

        if (file.exists()) {

            file.delete();
        }
    }
    //------------------------------------------------------------------------

}
