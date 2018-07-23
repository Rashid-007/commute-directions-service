package com.commute.direction.domain;

import lombok.Getter;
import org.apache.commons.lang.math.DoubleRange;
import org.apache.commons.lang.math.Range;
import org.springframework.util.Assert;

import java.util.regex.Pattern;

import static com.commute.direction.DirectionError.GEOPOINT_NOT_VALID;

@Getter
public class GeoPoint {

    public static final Range LAT_RANGE = new DoubleRange(-90.0, 90.0);
    public static final Range LNG_RANGE = new DoubleRange(-180.0, 180.0);
    private static final long serialVersionUID = 1L;
    private double lat;

    private double lng;

    private static Pattern geoPointFormat = Pattern.compile("[-]?[0-9]+([.][0-9]+)?[,]{1}[-]?[0-9]+([.][0-9]+)?");

    public GeoPoint(double lat, double lng) {
        Assert.isTrue(LAT_RANGE.containsDouble(lat), GEOPOINT_NOT_VALID.build());
        Assert.isTrue(LNG_RANGE.containsDouble(lng), GEOPOINT_NOT_VALID.build());

        this.lat = lat;
        this.lng = lng;
    }

    public static GeoPoint parse(String geoPoint){
        Assert.hasText(geoPoint, GEOPOINT_NOT_VALID.build());
        Assert.isTrue(geoPointFormat.matcher(geoPoint).matches(), GEOPOINT_NOT_VALID.build());

        String[] originLatLng = geoPoint.split(",");

        Assert.isTrue(LAT_RANGE.containsDouble(Double.parseDouble(originLatLng[0])), GEOPOINT_NOT_VALID.build());
        Assert.isTrue(LNG_RANGE.containsDouble(Double.parseDouble(originLatLng[1])), GEOPOINT_NOT_VALID.build());

        double lat = Double.parseDouble(originLatLng[0]);
        double lng = Double.parseDouble(originLatLng[1]);

        return new GeoPoint(lat, lng);
    }
}
