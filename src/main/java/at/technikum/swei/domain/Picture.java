package at.technikum.swei.domain;

import com.sun.istack.internal.NotNull;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "picture")
public class Picture implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true)
  private Long id;

  @Column(name = "name")
  private String name;

  @ManyToOne
  @JoinColumn(name = "fk_photographer")
  private Photographer photographer;


  @OneToOne(mappedBy = "picture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private EXIF exif;

  @OneToOne(mappedBy = "picture", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
  private IPTC iptc;

  public void addExif(EXIF exif) {
    exif.setPicture(this);
    this.exif = exif;
  }

  public void removeExif() {
    if (exif != null) {
      exif.setPicture(null);
      this.exif = null;
    }
  }

  public void addIptc(IPTC iptc) {
    iptc.setPicture(this);
    this.iptc = iptc;
  }

  public void removeIptc() {
    if (iptc != null) {
      iptc.setPicture(null);
      this.iptc = null;
    }
  }

  @Override
  public String toString() {

    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("Picture{");
    stringBuilder.append("id=" + id);
    stringBuilder.append(", name='" + name + '\'');

    if (photographer != null) {
      stringBuilder.append(
          ", photographer=" + photographer.getFirstName() + " " + photographer.getLastName());
    }

    stringBuilder.append(", exif=" + exif);
    stringBuilder.append(", iptc=" + iptc);
    stringBuilder.append("}");

    return stringBuilder.toString();
  }
}
