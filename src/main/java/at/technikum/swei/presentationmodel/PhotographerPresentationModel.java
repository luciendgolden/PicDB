package at.technikum.swei.presentationmodel;

import at.technikum.swei.domain.Photographer;
import java.time.LocalDate;

public class PhotographerPresentationModel {

  private Photographer photographer;



  public PhotographerPresentationModel(Photographer photographer) {
    this.photographer = photographer;
  }

  public PhotographerPresentationModel(){
    this.photographer = new Photographer();
  }

  public boolean isValid(){
    LocalDate today = LocalDate.now();
    if(photographer.getLastName()==null || photographer.getLastName().equals("")){
      return false;
    } else if(photographer.getBirthDate()==null ||
        photographer.getBirthDate().compareTo(today)>=0){
      return false;
    }else if(photographer.getNotes()==null || photographer.getNotes().equals("")){
      photographer.setNotes("nein");
    }
    return true;
  }

  public Photographer getPhotographer(){
    return this.photographer;
  }


  public void setFirstname(String firstname) {
    photographer.setFirstName(firstname);
  }

  public void setLasname(String lastname) {
     photographer.setLastName(lastname);
  }

  public void setBirthDate(LocalDate birthDate) {
    photographer.setBirthDate(birthDate);
  }

  public void setNotes(String notes) {
    photographer.setNotes(notes);
  }

  public long getID() {
    return photographer.getId();
  }

  public String getFirstname() {
    return photographer.getFirstName();
  }

  public String getLastName() {
    return photographer.getLastName();
  }

  public LocalDate getBirthDate() {
    return photographer.getBirthDate();
  }

  public String getNotes() {
    return photographer.getNotes();
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();

    builder.append("ID: ");
    builder.append(getID());
    builder.append(". ");
    builder.append(getFirstname());
    builder.append(" ");
    builder.append(getLastName());

    return builder.toString();
  }
}