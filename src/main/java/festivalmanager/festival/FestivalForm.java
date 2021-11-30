package festivalmanager.festival;

public class FestivalForm {

    private long locationId;

    private String beginDate;
    private String endDate;
    private String name;
    private String information;

    public long getLocationId() {
        return this.locationId;
    }

    public String getBeginDate() {
        return this.beginDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public String getName() {
        return this.name;
    }

    public String getInformation() {
        return this.information;
    }

    public FestivalForm(long locationId, String beginDate, String endDate, String name, String information) {
        this.locationId = locationId;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.name = name;
        this.information = information;
    }

}
