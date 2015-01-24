package kduraj.mysql;

public class ResultObject {

    private int npid;
    private String provider_full_name;
    private String insurance_plan;
    private double distance;

    public int getNpid() {
        return npid;
    }

    public void setNpid(int npid) {
        this.npid = npid;
    }

    public String getProvider_full_name() {
        return provider_full_name;
    }

    public void setProvider_full_name(String provider_full_name) {
        this.provider_full_name = provider_full_name;
    }

    public String getInsurance_plan() {
        return insurance_plan;
    }

    public void setInsurance_plan(String insurance_plan) {
        this.insurance_plan = insurance_plan;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
