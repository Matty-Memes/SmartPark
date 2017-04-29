package seshclub7.smartpark;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Matt on 29/04/2017.
 */

public class Street
{
    private String streetName;
    private LatLng latLng;
    private int StreetID;
    private int numberOfSpaces;

    public Street(String streetName, LatLng latLng, int streetID, int numberOfSpaces)
    {
        this.streetName = streetName;
        this.latLng = latLng;
        StreetID = streetID;
        this.numberOfSpaces = numberOfSpaces;
    }
    private String [] names = {"Howard Street",""};

}
