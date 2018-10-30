package Model;

import java.sql.Timestamp;

public class CapteurData {

    private String gps;
    private CapteurDataType type;
    private double value;
    private Timestamp timestamp;

    public CapteurData(CapteurdataEntity capteurdataEntity){
        this.gps = capteurdataEntity.getGps();
        this.timestamp = capteurdataEntity.getTimestamp();
        this.value = capteurdataEntity.getValue();
        switch (capteurdataEntity.getType()){
            case 1: this.type = CapteurDataType.TEMPERATURE; break;
            case 2: this.type = CapteurDataType.PRESSION; break;
            case 3: this.type = CapteurDataType.HUMIDITE; break;
            default: this.type = CapteurDataType.VITESSE_VENT; break;
        }
    }

    public String getGps() {
        return gps;
    }

    public void setGps(String gps) {
        this.gps = gps;
    }

    public CapteurDataType getType() {
        return type;
    }

    public void setType(CapteurDataType type) {
        this.type = type;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}
