package festivalmanager.festival;

import festivalmanager.authentication.UserManagement;
import festivalmanager.stock.StockEventListener;
import festivalmanager.stock.StockManagment;

import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import festivalmanager.catering.CateringManagement;
import festivalmanager.lineup.LineUp;
import festivalmanager.lineup.LineUpManagement;
import festivalmanager.location.Location;
import festivalmanager.location.LocationManagement;
import festivalmanager.communication.CommunicationManagement;
import festivalmanager.finance.FinanceManagement;

@Service
@Transactional
public class FestivalManagement {

    private final FestivalRepository festivalRepository;

    // managements
    private final LocationManagement locationManagement;
    private final CateringManagement cateringManagement;
    private final LineUpManagement lineUpManagement;
    private final UserManagement userManagement;
    private final CommunicationManagement communicationManagement;
    private final StockEventListener stockEventListener;
    private final FinanceManagement financeManagement;
    private final StockManagment stockManagment;

    /**
     * Constructor
     * 
     * @param festivalRepository
     * @param locationManagement
     * @param cateringManagement
     * @param lineUpManagement
     * @param userManagement
     * @param communicationManagement
     * @param stockEventListener
     * @param financeManagement
     * @param stockManagment
     */
    FestivalManagement(FestivalRepository festivalRepository, LocationManagement locationManagement,
            CateringManagement cateringManagement, LineUpManagement lineUpManagement, UserManagement userManagement,
            CommunicationManagement communicationManagement, StockEventListener stockEventListener,
            FinanceManagement financeManagement, StockManagment stockManagment) {
        Assert.notNull(festivalRepository, "festivalRepository must not be null");
        Assert.notNull(festivalRepository, "festivalRepository must not be null");
        Assert.notNull(locationManagement, "locationManagement must not be null");
        Assert.notNull(cateringManagement, "cateringManagement must not be null");
        Assert.notNull(lineUpManagement, "lineUpManagement must not be null");
        Assert.notNull(communicationManagement, "userManagement must not be null");
        Assert.notNull(financeManagement, "financeManagement must not be null");
        Assert.notNull(stockManagment, "stockManagment must not be null");
        this.festivalRepository = festivalRepository;
        this.locationManagement = locationManagement;
        this.cateringManagement = cateringManagement;
        this.cateringManagement.setFestivalManagement(this);
        this.lineUpManagement = lineUpManagement;
        this.lineUpManagement.setFestivalManagement(this);
        this.userManagement = userManagement;
        this.userManagement.setFestivalManagement(this);
        this.communicationManagement = communicationManagement;
        this.stockEventListener = stockEventListener;
        this.stockEventListener.setFestivalManagement(this);
        this.financeManagement = financeManagement;
        this.financeManagement.setFestivalManagement(this);
        this.stockManagment = stockManagment;
        this.stockManagment.setFestivalManagement(this);
    }

    public LineUpManagement getLineUpManagement() {
        return this.lineUpManagement;
    }

    public UserManagement getUserManagement() {
        return this.userManagement;
    }

    public CateringManagement getCateringManagement() {
        return this.cateringManagement;
    }

    public CommunicationManagement getCommunicationManagement() {
        return this.communicationManagement;
    }
    public FinanceManagement getFinanceManagement() {
        return this.financeManagement;
    }

    /**
     * Create a new festival
     * 
     * @param festival
     * @return
     */
    public Festival createFestival(Festival festival) {
        Assert.notNull(festival, "festival must not be null");
        if (festival.getLocation() == null) {
            festival.setLocation(locationManagement.findById(festival.getLocationIdentifier()));
        }
        festival.setIsPublished(true);
        return festivalRepository.save(festival);
    }

    /**
     * Create a new festival with parameters
     * 
     * @param name
     * @param location
     * @param beginDate
     * @param endDate
     * @param information
     * @return
     */
    public Festival createFestival(String name, Location location, String beginDate, String endDate,
            String information) {
        Festival festival = new Festival(name, location, beginDate, endDate, information);
        festival.setIsPublished(true);
        return createFestival(festival);
    }

    /**
     * Create a new festival with parameters
     * 
     * @param name
     * @param location
     * @param beginDate
     * @param endDate
     * @param information
     * @param isPublished
     * @return
     */
    public Festival createFestival(String name, Location location, String beginDate, String endDate,
            String information, boolean isPublished) {
        Festival festival = new Festival(name, location, beginDate, endDate, information);
        festival.setIsPublished(isPublished);
        return createFestival(festival);
    }

    /**
     * Returns all festivals
     * 
     * @return
     */
    public Streamable<Festival> findAllFestivals() {
        return festivalRepository.findAll();
    }

    /**
     * Returns all published festivals
     * 
     * @return
     */
    public Streamable<Festival> findAllPublishedFestivals() {
        return festivalRepository.findAll().filter(festival -> festival.getIsPublished());
    }

    /**
     * Returns the festival with the given identifier
     * 
     * @param id
     * @return
     */
    public Festival findById(long id) {
        return festivalRepository.findById(id).orElse(null);
    }

    /**
     * Returns all festivals with the given name
     * 
     * @param name
     * @return
     */
    public Streamable<Festival> findAllByName(String name) {
        return festivalRepository.findAll().filter(festival -> festival.getName().equals(name));
    }

    /**
     * removes the festival with the given identifier
     * 
     * @param id
     * @return
     */
    public boolean deleteById(long id) {
		cateringManagement.deleteFoodsByFestival_Id(id);
		stockManagment.deleteFoodInventoryItemsByFestivalId(id);
		userManagement.deleteUsersByFestivalId(id);
        getLineUpManagement().deleteById(id);
        festivalRepository.deleteById(id);
        return true;
    }

    /**
     * Updates the festival
     * 
     * @param festival
     * @return
     */
    public Festival updateFestival(Festival festival) {
        Assert.notNull(festival, "festival must not be null");
        if (festival.getLocation() == null) {
            System.out.println("leeeeeeeeeeeeeeeeeel");
            festival.setLocation(locationManagement.findById(festival.getLocationIdentifier()));
            System.out.println(festival.getLocationIdentifier());
        }
        System.out.println(festival.toString());
        return festivalRepository.save(festival);
    }

    /**
     * Publishes the festival with the given identifier
     * @param id the identifier of the festival
     * @return
     */
    public String publishById(long id) {
        Festival festival = findById(id);
        LineUp lineUp = lineUpManagement.findById(id);
        if (lineUp == null) {
            return "Festival has no lineups";
        }

        festival.setIsPublished(true);
        updateFestival(festival);

        return "ok";
    }

}
