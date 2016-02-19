package com.dmd.letutors.api;

import android.os.Environment;

/**
 *
 */
public class ApiConstants {

    public static final class Urls {
        public static final String TUTOR_API_URLS = "http://192.168.1.109:8080/TutorClient/";
    }

    public static final class Paths {
        public static final String BASE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath();
        public static final String IMAGE_LOADER_CACHE_PATH = "/Tutor/Images/";
    }

    public static final class Integers {
        public static final int PAGE_LAZY_LOAD_DELAY_TIME_MS = 200;
    }
}