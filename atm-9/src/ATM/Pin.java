package ATM;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pins")
public class Pin {

    @Id
    @Column
    private String cardNumber;
    @Column
    private String pinNumber;

    public Pin(){
        this.cardNumber = null;
        this.pinNumber = null;
    }
    public Pin(String cardNumber, String pinNumber) {
        this.cardNumber = cardNumber;
        this.pinNumber = pinNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPinNumber() {
        return pinNumber;
    }

    public void setPinNumber(String pinNumber) {
        this.pinNumber = pinNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pin pin = (Pin) o;

        if (cardNumber != null ? !cardNumber.equals(pin.cardNumber) : pin.cardNumber != null) return false;
        if (pinNumber != null ? !pinNumber.equals(pin.pinNumber) : pin.pinNumber != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cardNumber != null ? cardNumber.hashCode() : 0;
        result = 31 * result + (pinNumber != null ? pinNumber.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Pin{" +
                "cardNumber='" + cardNumber + '\'' +
                ", pinNumber='" + pinNumber + '\'' +
                '}';
    }
}
