package beit.employee.employeetrackemployee;

/**
 * Created by Dell on 24-Jan-18.
 */

    public class fbase {
        private String name;
        private String lat, lon, time,imei,session;
        private String uname,pwd;
        private String mobile,address;

        public fbase(){
        }

        public String getAddress(){
            return address;
        }

        public void setAddress(String address){
            this.address=address;
        }

        public String getMobile(){
            return mobile;
        }
        public void setMobile(String mobile){
            this.mobile=mobile;
        }

        public String getUname(){
        return uname;
    }
        public void setUname(String uname){
        this.uname=uname;
    }

        public String getPwd(){
        return pwd;
    }
        public void setPwd(String pwd){
        this.pwd=pwd;
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

