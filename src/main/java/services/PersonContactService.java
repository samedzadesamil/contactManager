/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import model.Person;

import java.util.List;

/**
 * @author DELL LATITUDE
 */

public interface PersonContactService {

    void writeToFile(Person person);

    long getLatestPersonId();

    List<Person> readPersonsFromFile();

    String getFilePath();
}
