package comele.example.admin.guoan.bean;

/**
 * Created by admin on 2017/9/27.
 */
public class DeviceInfo {

    //params from device
    @JsonColunm(name = "imei")
    public String imei;
    @JsonColunm(name = "mac")
    public String mac;
    @JsonColunm(name = "platform")
    public int platform;
    @JsonColunm(name = "model")
    public String model;
    @JsonColunm(name = "version")
    public String version;
    @JsonColunm(name = "country")
    public String country;
    @JsonColunm(name = "language")
    public String language;
    @JsonColunm(name = "network")
    public String network;
    @JsonColunm(name = "eid")
    public String eid;
    @JsonColunm(name = "signature")
    public String signature;
    @JsonColunm(name = "timestamp")
    public String timestamp;
    @JsonColunm(name = "random")
    public String random;
    @JsonColunm(name = "app_version")
    public String app_version;
    @JsonColunm(name = "device_id")
    public String device_id;
}
