package beit.employee.employeetrackemployee;

/**
 * Created by Dell on 24-Jan-18.
 */

    public class fbase {
        private String name;
        private String lat, lon, time,imei,session;

        public fbase(){
        }

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name=name;
        }

        public String getlat(){
            return lat;
        }

        public String getlon(){
            return lon;
        }
        public String gettime(){
            return time;
        }
        public String getImei(){
        return imei;
    }
        public String getSession(){
        return session;
    }


        public void setlat(String lat){ this.lat=lat;   }
        public void setlon(String lon){
            this.lon=lon;
        }
        public void settime(String time){ this.time=time; }
        public void setImei(String imei){ this.imei=imei; }
        public void setSession(String session){ this.session=session; }

    }

