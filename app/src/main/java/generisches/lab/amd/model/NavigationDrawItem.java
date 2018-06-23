package generisches.lab.amd.model;

import java.util.ArrayList;
import java.util.List;

import generisches.lab.amd.R;

public class NavigationDrawItem {
    private String title;
    private int imageId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    private static int[] getImages() {
        return new int[]{
                R.drawable.ic_animal, R.drawable.ic_birds,
                R.drawable.ic_forest, R.drawable.ic_ocean,
                R.drawable.ic_planet, R.drawable.ic_landscape};
    }
    private static String[] getTitles(){
        return new String[]{
            "Animals", "Birds", "Forest", "Oceans", "Planets", "Landscape"
        };
    }

    public static List<NavigationDrawItem> getData(){
        List<NavigationDrawItem> datalist = new ArrayList<>();
        int[] imageIDs = getImages();
        String[] titles = getTitles();

        for(int i = 0; i<titles.length; i++){
            NavigationDrawItem navItem = new NavigationDrawItem();
            navItem.setTitle(titles[i]);
            navItem.setImageId(imageIDs[i]);
            datalist.add(navItem);
        }
        return datalist;
    }
}
