import java.math.BigDecimal;
import java.sql.Date;

public class Event {

  private Date StartDate;
  private Date EndDate;
  private String Place;
  private BigDecimal Price;
  private boolean isPaid;

  public Date getStartDate() {
    return StartDate;
  }

  public void setStartDate(Date startDate) {
    StartDate = startDate;
  }

  public Date getEndDate() {
    return EndDate;
  }

  public void setEndDate(Date endDate) {
    EndDate = endDate;
  }

  public String getPlace() {
    return Place;
  }

  public void setPlace(String place) {
    Place = place;
  }

  public BigDecimal getPrice() {
    return Price;
  }

  public void setPrice(BigDecimal price) {
    Price = price;
  }

  public boolean isPaid() {
    return isPaid;
  }

  public void setPaid(boolean paid) {
    isPaid = paid;
  }
}
