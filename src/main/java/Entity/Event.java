package Entity;
import java.math.BigDecimal;
import java.util.Date;

public class Event {
  private Integer id;
  private Date startDate;
  private Date endDate;
  private String place;
  private BigDecimal price;
  private boolean isPaid;

  // Getters y setters
  public Date getStartDate() { return startDate; }
  public void setStartDate(Date startDate) { this.startDate = startDate; }

  public Date getEndDate() { return endDate; }
  public void setEndDate(Date endDate) { this.endDate = endDate; }

  public String getPlace() { return place; }
  public void setPlace(String place) { this.place = place; }

  public BigDecimal getPrice() { return price; }
  public void setPrice(BigDecimal price) { this.price = price; }

  public boolean isPaid() { return isPaid; }
  public void setPaid(boolean isPaid) { this.isPaid = isPaid; }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
}
