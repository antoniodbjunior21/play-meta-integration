package beans.facebook;


/*
* This is something called DTO that received the JSON and grab it.
* */
public class Place{

    private String name;
    private String id;
    private String created_time;
    private String country;
    private String city;
    private String latitude;
    private String located_in;
    private String longitude;


    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getCreated_time()
    {
        return created_time;
    }

    public void setCreated_time(String created_time)
    {
        this.created_time = created_time;
    }

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
        return "Place{" + "name='" + name + '\'' + ", id='" + id + '\'' + ", created_time='" + created_time + '\'' + ", country='" + country + '\'' + ", city='" + city + '\'' + ", latitude='" + latitude + '\'' + ", located_in='" + located_in + '\'' + ", longitude='" + longitude + '\'' + '}';
    }
}
