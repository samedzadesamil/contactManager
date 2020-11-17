package services;

import model.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PersonContactFileService implements PersonContactService {//test ele duz, ela, aydindi bu? aha
    // indi bize lazmdi 1. maven ve 2. git
    // 3. dependency injection bulari oxumalisan 2-3 gune, ki daha cetin bir taska girise bilesen
    //aydin ? aha bawlayacam ilk 2 denesi sadedi, 3 cu cetindi, onu ozm izah eleyecem onsuzda ama elegoz ucu baxarsan
    //yaxsi, baska sual ? yoxdu cox sagol buyur


    @Override
    public void writeToFile(Person person) {

       if (person.getId() == 0) {
            person.setId(PersonIdHolder.getInstance().nextId());
        }
        final String targetPath = getFilePath();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetPath, true))) {
            writer.write(person.toString());
            writer.newLine();
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    //---------------------------------------------------------------------

    @Override
    public List<Person> readPersonsFromFile() {
        String filePath = getFilePath();
        List<Person> resultPersonList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            String currentLine;

            while ((currentLine = bufferedReader.readLine()) != null) {
                String[] fields = currentLine.split(" ");
                Person person = new Person(Long.parseLong(fields[0]), fields[1], fields[2], fields[3]);
                resultPersonList.add(person);
            }

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return resultPersonList;
    }

    //---------------------------------------------------------------------------

    public long getLatestPersonId() {
        long personId = 0;
        String filePath = getFilePath();
        String currentLineId;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath))) {
            while ((currentLineId = bufferedReader.readLine()) != null) {
                String[] fields = currentLineId.split(" ");
                personId = Long.parseLong(fields[0]);
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return personId;
    }

    //--------------------------------------------------------------------------------

    public String getFilePath() {
        return PersonApplicationProperties.getInstance()
                .getValueOf("contact-app.contact.file.target.path");
    }
}
