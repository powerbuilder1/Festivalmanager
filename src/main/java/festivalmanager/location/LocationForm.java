package festivalmanager.location;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.NotEmpty;

public class LocationForm {

    private final @NotEmpty int maxVisitors;

    private final @NotEmpty int maxStages;
    private final @NotEmpty String name;
    private final @NotEmpty double rent;

    public LocationForm(int maxVisitors, int maxStages, String name, double rent) {
        this.maxVisitors = maxVisitors;
        this.maxStages = maxStages;
        this.name = name;
        this.rent = BigDecimal.valueOf(rent).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public int getMaxVisitors() {
        return this.maxVisitors;
    }

    public int getMaxStages() {
        return this.maxStages;
    }

    public String getName() {
        return this.name;
    }

    public double getRent() {
        return this.rent;
    }

}
