package controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Person;
import services.PersonContactFileService;
import services.PersonContactService;
import services.PersonDataRemoveFileService;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author DELL LATITUDE
 */
public class ContactUiController implements Initializable {

    //------------------------------------------------------------------------

    private final PersonDataRemoveFileService removeData = new PersonDataRemoveFileService();
    private final PersonContactService personContactService = new PersonContactFileService();

    //--------------------------------------------------------------------------------

    @FXML
    private Label removeLabel;
    @FXML
    private Label addLabel;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtSurname;
    @FXML
    private TextField txtNumber;

    //-------------------------------------------------------

    @FXML
    private TableView<Person> personTableView;
    @FXML
    private TableColumn<Person, String> idColumn;
    @FXML
    private TableColumn<Person, String> nameColumn;
    @FXML
    private TableColumn<Person, String> surnameColumn;
    @FXML
    private TableColumn<Person, String> numberColumn;

    //--------------------------------------------------------------

    @FXML
    private void addContact(MouseEvent event) {
        Person person = new Person(txtName.getText(), txtSurname.getText(), txtNumber.getText());
        personContactService.writeToFile(person);
        personTableView.getItems().add(person);
        addLabel.setText("add... ");
    }

    //----------------------------------------------------------------

    @FXML
    private void removeContact(MouseEvent event) {
        Person selectedItem = personTableView.getSelectionModel().getSelectedItem();
        personTableView.getItems().removeAll(selectedItem);
        removeData.deletePersonById(selectedItem.getId());
        removeLabel.setText("removed id: " + selectedItem.getId());
    }

    //-------------------------------------------------------------------

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        configureColumns();
        personTableView.setItems(FXCollections.observableArrayList());
        collectAllPersonsFromFile();
    }

    //---------------------------------------------------------------

    private void collectAllPersonsFromFile() {
        List<Person> personList = personContactService.readPersonsFromFile();
        personTableView.getItems().addAll(personList);
    }

    //------------------------------------------------------

    private void configureColumns() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
    }

    //------------------------------------------------------------------------------
}
