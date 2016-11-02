package com.example.cdm.huntfun.pojo;

import java.util.List;

/**
 * Created by cdm on 2016/11/1.
 */
public class WeatherInfo {

    public String resultcode;
    public String reason;
    public String error_code;
    public Result result;

    public class Result{
        public Sk sk;
        public Today today;
        public List<Futuer> future;

        @Override
        public String toString() {
            return "Result{" +
                    "sk=" + sk +
                    ", today=" + today +
                    ", futuer=" + future +
                    '}';
        }
    }

    public class Sk{
        public String temp;
        public String wind_direction;
        public String wind_strength;
        public String humidity;
        public String time;

        @Override
        public String toString() {
            return "Sk{" +
                    "temp='" + temp + '\'' +
                    ", wind_direction='" + wind_direction + '\'' +
                    ", wind_strength='" + wind_strength + '\'' +
                    ", humidity='" + humidity + '\'' +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    public class Today{
        public String temperature;
        public String weather;
        public String wind;
        public String week;
        public String city;
        public String date_y;
        public String dressing_index;
        public String dressing_advice;
        public String uv_index;
        public String comfort_index;
        public String wash_index;
        public String travel_index;
        public String exercise_index;
        public String drying_index;
        public WeatherId weather_id;

        @Override
        public String toString() {
            return "Today{" +
                    "temperature='" + temperature + '\'' +
                    ", weather='" + weather + '\'' +
                    ", wind='" + wind + '\'' +
                    ", week='" + week + '\'' +
                    ", city='" + city + '\'' +
                    ", date_y='" + date_y + '\'' +
                    ", dressing_index='" + dressing_index + '\'' +
                    ", dressing_advice='" + dressing_advice + '\'' +
                    ", uv_index='" + uv_index + '\'' +
                    ", comfort_index='" + comfort_index + '\'' +
                    ", wash_index='" + wash_index + '\'' +
                    ", travel_index='" + travel_index + '\'' +
                    ", exercise_index='" + exercise_index + '\'' +
                    ", drying_index='" + drying_index + '\'' +
                    ", weather_id=" + weather_id +
                    '}';
        }
    }

    public class Futuer{
        public String temperature;
        public String weather;
        public String wind;
        public String week;
        public String date;
        public WeatherId weather_id;

        @Override
        public String toString() {
            return "Futuer{" +
                    "temperature='" + temperature + '\'' +
                    ", weather='" + weather + '\'' +
                    ", wind='" + wind + '\'' +
                    ", week='" + week + '\'' +
                    ", date='" + date + '\'' +
                    ", weather_id=" + weather_id +
                    '}';
        }
    }

    public class WeatherId{
        public String fa;
        public String fb;

        @Override
        public String toString() {
            return "WeatherId{" +
                    "fa='" + fa + '\'' +
                    ", fb='" + fb + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WeatherInfo{" +
                "resultcode='" + resultcode + '\'' +
                ", reason='" + reason + '\'' +
                ", error_code='" + error_code + '\'' +
                ", result=" + result +
                '}';
    }
}
