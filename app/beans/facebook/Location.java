package beans.facebook;

public class Location{

    private String country;
    private String city;
    private String latitude;
    private String located_in;
    private String longitude;


    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }

    public String getLocated_in()
    {
        return located_in;
    }

    public void setLocated_in(String located_in)
    {
        this.located_in = located_in;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return "Location{" + "country='" + country + '\'' + ", city='" + city + '\'' + ", latitude='" + latitude + '\'' + ", located_in='" + located_in + '\'' + ", longitude='" + longitude + '\'' + '}';
    }
}
