package com.bonplan.gui;

import static com.bonplan.gui.EventsAll.accueil;
import com.codename1.googlemaps.MapContainer;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.maps.Coord;
import com.codename1.maps.providers.GoogleMapsProvider;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.Style;
import com.codename1.util.Callback;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author souab
 */
public class EventsDirections {

    private static final String MAPS_KEY = "AIzaSyDzwkxwIz7hgPCcMwhK2nogMGtTnAUlYzo";
    Form f;

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public EventsDirections(Coord src, Coord dest, Form back) {
        f = new Form("Map");
        f.getToolbar().addMaterialCommandToSideMenu("Home", FontImage.MATERIAL_HOME, e -> {
            accueil.showBack();
        });
        f.getToolbar().addMaterialCommandToSideMenu("All Events", FontImage.MATERIAL_HOME, e -> {
            EventsAll all = new EventsAll();
        });
        f.getToolbar().addMaterialCommandToSideMenu("My Participations", FontImage.MATERIAL_EVENT_SEAT, e -> {
            EventslistParticipants mesParticipations = new EventslistParticipants(f);
        });
        f.getToolbar().addMaterialCommandToSideMenu("My Events", FontImage.MATERIAL_EVENT, e -> {
            EventsMy myevents = new EventsMy(f);
        });
        MapContainer map = new MapContainer(new GoogleMapsProvider(MAPS_KEY));
        map.setCameraPosition(src);
        map.zoom(src, 5);
//        Coord src = new Coord(31.2001, 29.9187);
//        Coord dest = new Coord(30.0444, 31.2357);
        // get the routes using google directions api
        String encoded = getRoutesEncoded(src, dest);
        // decode the routes in an arry of coords
        Coord[] coords = decode(encoded);
        Style s = new Style();
        s.setBgTransparency(0);
        s.setFgColor(0x007700);
        map.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), src, "", "", null);
        map.addMarker(FontImage.createMaterial(FontImage.MATERIAL_LOCATION_ON, s).toEncodedImage(), dest, "", "", null);

        map.addPath(coords);
        System.out.println(coords.length);
        f.add(map);
        f.getToolbar().addCommandToRightBar("Back", FontImage.createMaterial(FontImage.MATERIAL_ARROW_BACK, new Style()), (e) -> {
            back.showBack();

        });
        f.show();
    }

    public static Coord[] decode(final String encodedPath) {
        int len = encodedPath.length();
        final ArrayList<Coord> path = new ArrayList<Coord>();
        int index = 0;
        int lat = 0;
        int lng = 0;

        while (index < len) {
            int result = 1;
            int shift = 0;
            int b;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lat += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            result = 1;
            shift = 0;
            do {
                b = encodedPath.charAt(index++) - 63 - 1;
                result += b << shift;
                shift += 5;
            } while (b >= 0x1f);
            lng += (result & 1) != 0 ? ~(result >> 1) : (result >> 1);

            path.add(new Coord(lat * 1e-5, lng * 1e-5));
        }
        Coord[] p = new Coord[path.size()];
        for (int i = 0; i < path.size(); i++) {
            p[i] = path.get(i);
        }

        return p;
    }

    public static String getRoutesEncoded(Coord src, Coord dest) {
        String ret = "";
        try {
            ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false);
            request.addArgument("key", MAPS_KEY);
            request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
            request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

            NetworkManager.getInstance().addToQueueAndWait(request);
            Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(new ByteArrayInputStream(request.getResponseData()), "UTF-8"));
            if (response.get("routes") != null) {
                ArrayList routes = (ArrayList) response.get("routes");
                if (routes.size() > 0) {
                    ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static void getRoutesEncodedAsync(Coord src, Coord dest, Callback callback) {
        ConnectionRequest request = new ConnectionRequest("https://maps.googleapis.com/maps/api/directions/json", false) {
            protected void readResponse(InputStream input) throws IOException {
                String ret = "";
                Map<String, Object> response = new JSONParser().parseJSON(new InputStreamReader(input, "UTF-8"));
                if (response.get("routes") != null) {
                    ArrayList routes = (ArrayList) response.get("routes");
                    if (routes.size() > 0) {
                        ret = ((LinkedHashMap) ((LinkedHashMap) ((ArrayList) response.get("routes")).get(0)).get("overview_polyline")).get("points").toString();
                    }
                }
                callback.onSucess(ret);
            }

        };
        request.addArgument("key", MAPS_KEY);
        request.addArgument("origin", src.getLatitude() + "," + src.getLongitude());
        request.addArgument("destination", dest.getLatitude() + "," + dest.getLongitude());

        NetworkManager.getInstance().addToQueue(request);
    }
}
