import java.sql.Date;

public class User {

  private String firstName;
  private String lastName;
  private Date dateOfBirth;
  private String id;
  private String idType;
  private boolean canTravel;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getIdType() {
    return idType;
  }

  public void setIdType(String idType) {
    this.idType = idType;
  }

  public boolean isCanTravel() {
    return canTravel;
  }

  public void setCanTravel(boolean canTravel) {
    this.canTravel = canTravel;
  }
}
