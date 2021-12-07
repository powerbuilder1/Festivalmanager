package festivalmanager.festival;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.util.Streamable;

import festivalmanager.Util.ServiceUtils;
import festivalmanager.location.LocationManagement;

@Configuration
public class FestivalValidator implements ConstraintValidator<FestivalConstraint, Festival> {

    @Override
    public void initialize(FestivalConstraint constraintAnnotation) {
    }

    @Override
    public boolean isValid(Festival value, ConstraintValidatorContext ctx) {
        Date beginDate = null;
        Date endDate = null;

        // name
        if (value.getName().length() < 4) {
            ctx.buildConstraintViolationWithTemplate("Festival name must be at least 4 characters long")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        if (value.getName().length() > 255) {
            ctx.buildConstraintViolationWithTemplate("Festival name must be at most 255 characters long")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        // location
        if (value.getLocation() == null && value.getLocationIdentifier() == 0) {
            ctx.buildConstraintViolationWithTemplate("Festival must have a location")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }

        // beginDate
        if (value.getBeginDate() == null || value.getBeginDate().isEmpty()) {
            ctx.buildConstraintViolationWithTemplate("BeginDate must not be empty")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        if (!value.getBeginDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            ctx.buildConstraintViolationWithTemplate("BeginDate must be in format YYYY-MM-DD")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.getBeginDate());
            Date today = new Date();
            if (date.before(today)) {
                ctx.buildConstraintViolationWithTemplate("BeginDate must be in the future")
                        .addConstraintViolation();
                System.out.println(value.toString());
                return false;
            }
            beginDate = date;
        } catch (Exception e) {
            ctx.buildConstraintViolationWithTemplate("BeginDate is not valid")
                    .addConstraintViolation();
            System.out.println(e.getMessage());
            System.out.println(value.toString());
            return false;
        }

        // endDate
        if (value.getEndDate() == null || value.getEndDate().isEmpty()) {
            ctx.buildConstraintViolationWithTemplate("EndDate must not be empty")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        if (!value.getEndDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            ctx.buildConstraintViolationWithTemplate("EndDate must be in format YYYY-MM-DD")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.getEndDate());
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(value.getBeginDate());
            if (date.before(begin)) {
                ctx.buildConstraintViolationWithTemplate("EndDate must take place after BeginDate")
                        .addConstraintViolation();
                System.out.println(value.toString());
                return false;
            }
            endDate = date;
        } catch (Exception e) {
            ctx.buildConstraintViolationWithTemplate("EndDate is not valid")
                    .addConstraintViolation();
            System.out.println(e.getMessage());
            System.out.println(value.toString());
            return false;
        }

        // location
        LocationManagement locationManagement = ServiceUtils.getLocationManagement();
        FestivalManagement festivalManagement = ServiceUtils.getFestivalManagement();

        if (value.getLocation() == null && value.getLocationIdentifier() != 0) {
            value.setLocation(locationManagement.findById(value.getLocationIdentifier()));
        }
        if (beginDate == null || endDate == null) {
            return false;
        }
        // check if location is not used in another festival during the time period of
        // beginDate and endDate

        Streamable<Festival> festivals = festivalManagement.findAllFestivals()
                .filter(festival -> festival.getLocation().equals(value.getLocation()));
        System.out.println("Count: " + festivals.toList().size());

        for (Festival festival : festivals) {
            System.out.println("Checking for festival " + festival.toString());
            try {
                Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(festival.getBeginDate());
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(festival.getEndDate());

                if (beginDate.before(begin) && endDate.after(begin)) {
                    ctx.buildConstraintViolationWithTemplate(
                            "Location is already used in another festival (" + festival.getName()
                                    + ") during the time period of "
                                    + begin.toString() + " and " + end.toString())
                            .addConstraintViolation();
                    return false;
                }
                if (begin.before(beginDate) && end.after(beginDate)) {
                    ctx.buildConstraintViolationWithTemplate(
                            "Location is already used in another festival (" + festival.getName()
                                    + ") during the time period of "
                                    + begin.toString() + " and " + end.toString())
                            .addConstraintViolation();
                    return false;
                }
            } catch (Exception e) {
                // should never happen because festivals are validated before
            }
        }

        return true;
    }

}
