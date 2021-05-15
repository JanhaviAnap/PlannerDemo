package planner;

import java.util.*;
public class Mood {
    public static HashMap<String, String> getMoodMap(){
        HashMap<String, String> moodMap = new HashMap<String, String>();
        moodMap.put("none", "#FFFFFF");
        moodMap.put("happy", "#F9A7B0");
        moodMap.put("gloomy", "#614051");
        moodMap.put("humorous", "#FFFF00");
        moodMap.put("calm", "#7FFFD4");
        moodMap.put("lighthearted", "#FFDFDD");
        moodMap.put("angry", "#800517");
        moodMap.put("cheerful", "#FF8040");
        moodMap.put("lonely", "#493D26");
        return moodMap;
    }

}
