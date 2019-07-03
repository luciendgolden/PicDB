package at.technikum.swei.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@Table(name = "exif")
public class EXIF implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", unique = true)
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "fk_picture_id")
  private Picture picture;

  @Column(name = "camera_vendor", columnDefinition="varchar(255)")
  private String cameraVendor;

  @Column(name = "flash", columnDefinition="varchar(255)")
  private String flash;

  @Column(name = "resolution", columnDefinition="varchar(255)")
  private String resolution;

  public EXIF() {
  }
}
