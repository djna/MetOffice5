package training.metofficeweather.data;

public class GreetingData {
   private String surname;
   private String locationSelected;
   public GreetingData(String initSurname , String initLocationSelected){
       surname = initSurname;
       locationSelected = initLocationSelected;
   }

    public String getSurname() {
        return surname;
    }

    public String getLocationSelected() {
        return locationSelected;
    }

    @Override
    public String toString() {
        return "GreetingData{" +
                "surname='" + surname + '\'' +
                ", locationSelected='" + locationSelected + '\'' +
                '}';
    }
}
