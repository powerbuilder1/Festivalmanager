package festivalmanager.finance;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.festival.FestivalRepository;
import festivalmanager.festival.Festival;
import festivalmanager.lineup.LineUp;
import festivalmanager.location.LocationManagement;
import festivalmanager.location.LocationRepository;
import festivalmanager.location.Location;
import org.salespointframework.accountancy.Accountancy;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import org.javamoney.moneta.Money;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Optional;


@Service
@Transactional
public class FinanceManagement {
	private final FinanceRepository financeRepository;
	private final FestivalRepository festivalRepository;
	private final FestivalManagement festivalManagement;
	private final LocationRepository locationRepository;
	private final LocationManagement locationManagement;


	public FinanceManagement(FinanceRepository financeRepository, FestivalRepository festivalRepository, FestivalManagement festivalManagement, LocationRepository locationRepository, LocationManagement locationManagement){
		Assert.notNull(financeRepository,"financeRepository must not be Null");
		Assert.notNull(festivalRepository,"festivalRepository must not be Null");
		Assert.notNull(festivalManagement,"festivalManagement must not be Null");
		Assert.notNull(locationRepository,"locationRepository must not be Null");
		Assert.notNull(locationManagement,"locationManagement must not be null");
		this.financeRepository = financeRepository;
		this.festivalRepository = festivalRepository;
		this.festivalManagement = festivalManagement;
		this.locationRepository = locationRepository;
		this.locationManagement= locationManagement;
	}

	public void visualizeFinance(){}

	public Money payLocation(long festivalId/*, Money rent*/){
		Festival festival = festivalManagement.findById(festivalId);
		Money rent = festival.getLocation().getRent();
		Streamable<Finance> balance= financeRepository.findAll();
		Money festivalrent=rent;
		Money festivalbalance=balance.toList().get(0).getBalance();
		festivalbalance.subtract(festivalrent);
		return festivalbalance;

	}

	public void payArtists(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		LineUp lineup = festivalManagement.getLineUpManagement().findById(festivalId);
		lineup.getBands();
	}

	public void payStaff(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		User user = festivalManagement.getUserManagement().findById(festivalId);
		user.getWorkPlace();
	}

	public void payItems(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		Money price = festivalManagement.gCateringManagement();

	}

	public void earnTicketSales(long festivalId){}

	public void earnCateringSales(long festivalId){}
}
