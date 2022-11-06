package pojos;

public class BookingdatesPojo {
    //Pojo olustururken field'da private access modifier ile datalari initialize edilip
    //parametresiz ve parametreli construct'lar olusturulup
    //getter setterler eklenip
    //yazdirmak icin toString eklenir
    private String checkin;
    private String checkout;

    public BookingdatesPojo() {
    }

    public BookingdatesPojo(String checkin, String checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    @Override
    public String toString() {
        return "BookingdatesPojo{" +
                "checkin='" + checkin + '\'' +
                ", checkout='" + checkout + '\'' +
                '}';
    }
}
