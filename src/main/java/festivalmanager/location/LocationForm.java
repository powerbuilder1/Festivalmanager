package festivalmanager.location;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class LocationForm {

    private final long id;
    private final @Positive int maxVisitors;

    private final @Positive int maxStages;
    private final @NotEmpty String name;
    private final @DecimalMin("0.00") double rent;

    public LocationForm(int maxVisitors, int maxStages, String name, double rent, long id) {
        this.maxVisitors = maxVisitors;
        this.maxStages = maxStages;
        this.name = name;
        this.rent = BigDecimal.valueOf(rent).setScale(2, RoundingMode.HALF_UP).doubleValue();
        this.id = id;
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

    public long getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", maxVisitors='" + getMaxVisitors() + "'" +
                ", maxStages='" + getMaxStages() + "'" +
                ", name='" + getName() + "'" +
                ", rent='" + getRent() + "'" +
                "}";
    }

}
