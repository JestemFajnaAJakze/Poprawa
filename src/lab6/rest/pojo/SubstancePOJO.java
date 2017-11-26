package lab6.rest.pojo;

public class SubstancePOJO {


    private String substanceId;
    private String substanceName;
    private String unit;
    private double treshold;

    public SubstancePOJO(){}

    public String getSubstanceId() {
        return substanceId;
    }

    public void setSubstanceId(String substanceId) {
        this.substanceId = substanceId;
    }

    public String getSubstanceName() {
        return substanceName;
    }

    public void setSubstanceName(String substanceName) {
        this.substanceName = substanceName;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public double getTreshold() {
        return treshold;
    }

    public void setTreshold(double treshold) {
        this.treshold = treshold;
    }



}