package at.technikum.swei.controller;

import static at.technikum.swei.Main.openPopup;

import at.technikum.swei.business.BusinessLayer;
import at.technikum.swei.button.mediator.Component;
import at.technikum.swei.button.mediator.ConcreteMediator;
import at.technikum.swei.button.mediator.Mediator;
import at.technikum.swei.domain.EXIF;
import at.technikum.swei.domain.IPTC;
import at.technikum.swei.domain.Photographer;
import at.technikum.swei.domain.Picture;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.util.StringConverter;

public class Controller {

  private final static String[] CAMERA_VENDORS = new String[]{
      "Sony",
      "Nikon",
      "Canon",
      "Panasonic",
  };

  private static final String[] COLOR_MARKS = new String[]{
      "Green",
      "Blue",
      "Yellow",
      "Pink",
  };

  @FXML
  public ComboBox<Photographer> photographerComboBox;


  private ObservableList<String> options;

  private ObservableList<Photographer> photographersObservable;

  @FXML
  public Button saveButton;

  @FXML
  public TextArea textArea;

  @FXML
  public TextField textField;

  @FXML
  public ComboBox comboBox;

  @FXML
  private ToggleButton iptcToggleButton;

  private BusinessLayer businessLayer;

  private boolean iptcClicked = true;

  private boolean exifClicked = false;

  private EXIF exif;
  private IPTC iptc;

  @FXML
  private ToggleButton exifToggleButton;

  private Picture selectedPicture;

  @FXML
  private MenuItem photographerButton;

  public void setIptcClicked(boolean iptcClicked) {
    this.iptcClicked = iptcClicked;
  }

  public void setExifClicked(boolean exifClicked) {
    this.exifClicked = exifClicked;
  }

  @FXML
  private void handlePhotographer(ActionEvent event) throws IOException {
    openPopup("photographerView");
  }

  @FXML
  void initialize() {
    Context.getInstance().setMainController(this);

    businessLayer = new BusinessLayer();
    Mediator<ToggleButton> buttonMediator = new ConcreteMediator();
    buttonMediator.registerComponent((Component) iptcToggleButton);
    buttonMediator.registerComponent((Component) exifToggleButton);

    loadPhotographers();
  }

  public void loadDaStuff(Path path) {
    String[] str = path.toUri().toString().split(File.separator);
    String fullname = str[str.length - 1];
    String name = fullname.split("\\.")[0];

    selectedPicture = businessLayer.getPictureByName(name);

    exif = businessLayer.getEXIFByPicId(selectedPicture);
    iptc = businessLayer.getIPTCByPicId(selectedPicture);

    if(exifClicked)
      exifLoad(null);

    if(iptcClicked)
      iptcLoad(null);



    if(selectedPicture.getPhotographer()!= null){
      photographerComboBox.getSelectionModel().select(selectedPicture.getPhotographer());
    }else{
      photographerComboBox.valueProperty().set(null);
    }
  }

  private void loadPhotographers() {
    List<Photographer> photographerList = businessLayer.getAllPhotographers();

    photographerList.forEach(Photographer::printPhotographer);

    photographersObservable = FXCollections.observableArrayList(photographerList);
    photographerComboBox.setItems(photographersObservable);

    photographerComboBox.setConverter(new StringConverter<Photographer>() {

      @Override
      public String toString(Photographer object) {
        return object.getName();
      }

      @Override
      public Photographer fromString(String string) {
        return photographerComboBox.getItems().stream().filter(ap ->
            ap.getName().equals(string)).findFirst().orElse(null);
      }
    });
  }

  public void exifLoad(ActionEvent actionEvent) {
    if (exif != null) {
      options = FXCollections.observableArrayList(
          "Sony",
          "Nikon",
          "Canon",
          "Panasonic"
      );

      comboBox.setItems(options);
      comboBox.getSelectionModel().select(exif.getCameraVendor());

      textField.setText(exif.getFlash());
      textArea.setText(exif.getResolution());
    }
  }

  public void iptcLoad(ActionEvent actionEvent) {
    if (iptc != null) {
      options = FXCollections.observableArrayList(
          "Green",
          "Blue",
          "Yellow",
          "Pink"
      );

      comboBox.setItems(options);
      comboBox.getSelectionModel().select(iptc.getColorMark());

      textField.setText(iptc.getReview());
      textArea.setText(iptc.getDescription());
    }
  }

  @FXML
  public void saveInformation(ActionEvent actionEvent) {
    if (exif != null || iptc != null) {
      String comboItem = (String) comboBox.getSelectionModel().getSelectedItem();
      String field = textField.getText();
      String area = textArea.getText();

      if (isEXIF()) {
        exif.setCameraVendor(comboItem);
        exif.setFlash(field);
        exif.setResolution(area);

        businessLayer.updateEXIF(exif);
      } else if (isIPTC()) {
        iptc.setColorMark(comboItem);
        iptc.setReview(field);
        iptc.setDescription(area);

        businessLayer.updateIPTC(iptc);
      }

      if(photographerComboBox.getSelectionModel().getSelectedItem()!=null){
        selectedPicture.setPhotographer(photographerComboBox.getSelectionModel().getSelectedItem());
        businessLayer.savePicture(selectedPicture);
      }
    }
  }

  private boolean isIPTC() {
    return Stream.of(COLOR_MARKS).anyMatch(comboBox.getSelectionModel().getSelectedItem()::equals);
  }

  private boolean isEXIF() {
    return Stream.of(CAMERA_VENDORS).anyMatch(comboBox.getSelectionModel().getSelectedItem()::equals);
  }
}
