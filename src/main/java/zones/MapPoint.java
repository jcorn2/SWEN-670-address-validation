package zones;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author bmurray
 */
public class MapPoint {

    private ImportKML newKML;
    private List<ZonePolygon> mapZones;

    // Define Infinite
    public MapPoint(){
        try {
            newKML = new ImportKML();
            // newKML.parseKML(); //uncomment this to import file on start

            mapZones = newKML.convertAllZonesToPolygon();


        } catch (Exception exc) {
            System.out.println(exc.getMessage());

        }
    }
    public List<ZonePolygon> getMapZones(){
        return mapZones;
    }
    public List<String> findZones(Double lat, Double lon) throws Exception {
        List<String> list = new ArrayList<>();
        int numZones = mapZones.size();
        int tempLat = (int) (lat * 1000000);
        int tempLon = (int) (lon * 1000000);

        try {
            int index1 = 0;
            while (index1 < numZones) {

                if(mapZones.get(index1).contains(tempLat,tempLon)){
                    list.add(mapZones.get(index1).getZoneCode());
                }
                index1++;
            }
            return list;
        }catch (Exception exc) {
            System.out.println(exc.getMessage());

        }
        return list;
    }

    // Driver Code
//    public static void main(String[] args) throws Exception
//    {
//        System.out.println("hello world");
//        try{
//            MapPoint startApp = new MapPoint();
//
//            //find zones and put in array
//            List<String>zones = startApp.findZones(-118.149303,34.179435);
//
//            //loop throug array and output zones
//            int index1 = 0;
//            if(zones.size() >0){
//                while (index1 < zones.size()) {
//                    System.out.println("in zone: " + zones.get(index1).toString());
//                    index1++;
//                }
//            }
//        }catch(Exception exc) {
//            System.out.println(exc.getMessage());
//        }
//    }

}

