package at.technikum.swei.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "photographer")
public class Photographer implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "first_name", columnDefinition="varchar(100)")
  private String firstName;

  @Column(name = "last_name", nullable = false, columnDefinition="varchar(50)")
  private String lastName;

  /**
   * MUST-HAVE: < Today()
   */
  @Column(name = "birth_date", columnDefinition = "date")
  private LocalDate birthDate;

  @Column(name = "notes", columnDefinition="varchar(255)")
  private String notes;

  @OneToMany(mappedBy = "photographer")
  private List<Picture> pictureList = new ArrayList<>();

  public void addPicture(Picture picture) {
    this.pictureList.add(picture);
    picture.setPhotographer(this);
  }

  @Override
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Photographer{");
    stringBuilder.append("id=" + id);
    stringBuilder.append(", firstName='" + firstName + '\'');
    stringBuilder.append(", lastName='" + lastName + '\'');
    stringBuilder.append(", birthDate=" + birthDate);
    stringBuilder.append(", notes='" + notes + '\'');

    String names = "";

    for(Picture p:pictureList){
      names+=p.getName()+",";
    }

    stringBuilder.append(", pictureList[" + names + "]");
    stringBuilder.append("}");

    return stringBuilder.toString();
  }

  public String printPhotographer() {
    return "Photographer{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", birthDate=" + birthDate +
        ", notes='" + notes + '\'' +
        '}';
  }

  public String getName(){
    return getFirstName() + " " + getLastName();
  }
}
