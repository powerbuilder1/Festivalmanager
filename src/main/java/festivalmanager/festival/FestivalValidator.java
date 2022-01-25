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

    /**
     * initialize the validator
     */
    @Override
    public void initialize(FestivalConstraint constraintAnnotation) {
        // Auto-generated method stub
    }

    /**
     * validate the festival
     * @param value the festival to validate
     * @param ctx the context of the validation
     */
    @Override
    public boolean isValid(Festival value, ConstraintValidatorContext ctx) {
        // simple with all checks in one place
        return (checkName(value, ctx) &&
                checkLocation(value, ctx) &&
                checkBeginDateFormat(value, ctx) &&
                checkEndDateFormat(value, ctx) &&
                checkBeginDate(value, ctx) &&
                checkEndDate(value, ctx) &&
                checkLocationFree(value, ctx));
    }

    /**
     * check if the festival name is valid
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the festival name is valid
     */
    private boolean checkName(Festival value, ConstraintValidatorContext ctx) {
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
        return true;
    }

    /**
     * check if the festival location is valid
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the festival location is valid
     */
    private boolean checkLocation(Festival value, ConstraintValidatorContext ctx) {
        // location
        if (value.getLocation() == null && value.getLocationIdentifier() == 0) {
            ctx.buildConstraintViolationWithTemplate("Festival must have a location")
                    .addConstraintViolation();
            System.out.println(value.toString());
            return false;
        }
        LocationManagement locationManagement = ServiceUtils.getLocationManagement();
        if (value.getLocation() == null && value.getLocationIdentifier() != 0) {
            value.setLocation(locationManagement.findById(value.getLocationIdentifier()));
        }
        return true;
    }

    /**
     * Checks if the begin date is well formatted
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the begin date is well formatted
     */
    private boolean checkBeginDateFormat(Festival value, ConstraintValidatorContext ctx) {
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
        return true;
    }

    /**
     * Checks if the end date is well formatted
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the end date is well formatted
     */
    private boolean checkEndDateFormat(Festival value, ConstraintValidatorContext ctx) {
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
        return true;
    }

    /**
     * Checks if the location is free
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the location is free
     */
    private boolean checkLocationFree(Festival value, ConstraintValidatorContext ctx) {
        FestivalManagement festivalManagement = ServiceUtils.getFestivalManagement();
        Streamable<Festival> festivals = festivalManagement.findAllFestivals()
                .filter(festival -> festival.getLocation().equals(value.getLocation()));
        System.out.println("Count: " + festivals.toList().size());

        for (Festival festival : festivals) {
            System.out.println("Checking for festival " + festival.toString());
            if(festival.getId() == value.getId()) {
                continue;
            }
            try {
                // already tested
                Date beginDate = new SimpleDateFormat("yyyy-MM-dd").parse(value.getBeginDate());
                Date endDate = new SimpleDateFormat("yyyy-MM-dd").parse(value.getEndDate());

                Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(festival.getBeginDate());
                Date end = new SimpleDateFormat("yyyy-MM-dd").parse(festival.getEndDate());

                if ((beginDate.before(begin) || beginDate.equals(begin))
                    && (endDate.after(begin) || endDate.equals(begin))) {
                    ctx.buildConstraintViolationWithTemplate(
                            "Location is already used in another festival (" + festival.getName()
                                    + ") during the time period of "
                                    + begin.toString() + " and " + end.toString())
                            .addConstraintViolation();
                    return false;
                }
                if ((begin.before(beginDate) || begin.equals(beginDate))
                    && (end.after(beginDate) || end.equals(beginDate))) {
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

    /**
     * Checks if the begin date is in the future and if it is well formatted
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the begin date is in the future and if it is well formatted
     */
    private boolean checkBeginDate(Festival value, ConstraintValidatorContext ctx) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.getBeginDate());
            Date today = new Date();
            if (date.before(today)) {
                ctx.buildConstraintViolationWithTemplate("BeginDate must be in the future")
                        .addConstraintViolation();
                System.out.println(value.toString());
                return false;
            }
        } catch (Exception e) {
            ctx.buildConstraintViolationWithTemplate("BeginDate is not valid")
                    .addConstraintViolation();
            System.out.println(e.getMessage());
            System.out.println(value.toString());
            return false;
        }
        return true;
    }

    /**
     * Checks if the end date is in the future and if it is well formatted
     * @param value the festival to validate
     * @param ctx the context of the validation
     * @return wether the end date is in the future and if it is well formatted
     */
    private boolean checkEndDate(Festival value, ConstraintValidatorContext ctx) {
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse(value.getEndDate());
            Date begin = new SimpleDateFormat("yyyy-MM-dd").parse(value.getBeginDate());
            if (date.before(begin)) {
                ctx.buildConstraintViolationWithTemplate("EndDate must take place after BeginDate")
                        .addConstraintViolation();
                System.out.println(value.toString());
                return false;
            }
        } catch (Exception e) {
            ctx.buildConstraintViolationWithTemplate("EndDate is not valid")
                    .addConstraintViolation();
            System.out.println(e.getMessage());
            System.out.println(value.toString());
            return false;
        }
        return true;
    }

}
