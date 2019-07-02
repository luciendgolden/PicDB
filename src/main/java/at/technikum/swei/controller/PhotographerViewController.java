package at.technikum.swei.controller;

import static at.technikum.swei.foundation.LocalDateUtil.convertStringToLocalDate;

import at.technikum.swei.business.BusinessLayer;
import at.technikum.swei.domain.Photographer;
import at.technikum.swei.presentationmodel.PhotographerPresentationModel;
import java.util.List;
import java.util.stream.Collectors;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.apache.log4j.Logger;

public class PhotographerViewController {

  private static final Logger logger = Logger.getLogger(PhotographerViewController.class);

  private ObservableList<PhotographerPresentationModel> items;

  @FXML
  private TextField firstName;

  @FXML
  private TextField lastName;

  @FXML
  private TextField birthDate;

  @FXML
  private TextArea notes;

  @FXML
  private ListView photographerListView;

  private BusinessLayer businessLayer;

  @FXML
  public void deletePhotographer(ActionEvent event) {
    final int selectedIdx = photographerListView.getSelectionModel().getSelectedIndex();
    if (selectedIdx != -1) {
      PhotographerPresentationModel itemToRemove = (PhotographerPresentationModel) photographerListView
          .getSelectionModel().getSelectedItem();

      final int newSelectedIdx =
          (selectedIdx == photographerListView.getItems().size() - 1)
              ? selectedIdx - 1
              : selectedIdx;

      businessLayer.deletePhotographer(itemToRemove.getPhotographer());

      photographerListView.getItems().remove(selectedIdx);
      photographerListView.getSelectionModel().select(newSelectedIdx);
    }
  }

  @FXML
  public void initialize() {
    this.businessLayer = new BusinessLayer();
    initializeListViewPhotographer();

    photographerListView.getSelectionModel().selectedItemProperty().addListener(
        (ChangeListener<PhotographerPresentationModel>) (observable, oldValue, newValue) -> {
          this.firstName.setText(newValue.getFirstname());
          this.lastName.setText(newValue.getLastName());
          this.birthDate.setText(newValue.getBirthDate().toString());
          this.notes.setText(newValue.getNotes());
        });
  }

  @FXML
  private void saveDaPhotoman(ActionEvent event) {
    final int selectedIdx = photographerListView.getSelectionModel().getSelectedIndex();

    PhotographerPresentationModel itemToSave = (PhotographerPresentationModel) photographerListView
        .getSelectionModel().getSelectedItem();

    PhotographerPresentationModel fallback = copyPhotographerPresentationModel(itemToSave);

    // Set before validate
    itemToSave.setFirstname(this.firstName.getText());
    itemToSave.setLasname(this.lastName.getText());
    itemToSave.setBirthDate(convertStringToLocalDate(this.birthDate.getText()));
    itemToSave.setNotes(this.notes.getText());

    if (itemToSave.isValid()) {
      businessLayer.updatePhotographer(itemToSave.getPhotographer());
      this.items.set(selectedIdx, itemToSave);
    } else {
      this.firstName.setText(fallback.getFirstname());
      this.lastName.setText(fallback.getLastName());
      this.notes.setText(fallback.getNotes());
      this.birthDate.setText(fallback.getBirthDate().toString());

      this.items.set(selectedIdx, fallback);

      logger.error("PhotographerPresentationModel can't be saved!");
    }
  }

  private PhotographerPresentationModel copyPhotographerPresentationModel(
      PhotographerPresentationModel itemToSave) {
    PhotographerPresentationModel ppm = new PhotographerPresentationModel();

    ppm.setID(itemToSave.getID());
    ppm.setFirstname(itemToSave.getFirstname());
    ppm.setLasname(itemToSave.getLastName());
    ppm.setNotes(itemToSave.getNotes());
    ppm.setBirthDate(itemToSave.getBirthDate());

    return ppm;
  }


  private void initializeListViewPhotographer() {
    this.items = FXCollections
        .observableArrayList(getAllPhotographersFromDb());

    photographerListView.setItems(items);
  }

  private List<PhotographerPresentationModel> getAllPhotographersFromDb() {
    List<Photographer> resultModels = businessLayer.getAllPhotographers();

    return resultModels.stream()
        .map(PhotographerPresentationModel::new)
        .collect(Collectors.toList());
  }
}
