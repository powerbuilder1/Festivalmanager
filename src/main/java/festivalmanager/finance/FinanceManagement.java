package festivalmanager.finance;

import static org.salespointframework.core.Currencies.*;

import festivalmanager.authentication.User;
import festivalmanager.authentication.UserForm;
import festivalmanager.catering.Food;
import festivalmanager.festival.FestivalManagement;
import festivalmanager.festival.FestivalRepository;
import festivalmanager.festival.Festival;
import festivalmanager.lineup.Band;
import festivalmanager.lineup.BandForm;
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

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class FinanceManagement {
	private final FinanceRepository financeRepository;
	private final FestivalManagement festivalManagement;


	public FinanceManagement(FinanceRepository financeRepository, FestivalRepository festivalRepository, FestivalManagement festivalManagement, LocationRepository locationRepository, LocationManagement locationManagement){
		Assert.notNull(financeRepository,"financeRepository must not be Null");
		Assert.notNull(festivalManagement,"festivalManagement must not be Null");
		this.financeRepository = financeRepository;
		this.festivalManagement = festivalManagement;
	}

	public void visualizeFinance(){}

	public Money payLocation(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		Money rent = festival.getLocation().getRent();
		Streamable<Finance> balance= financeRepository.findAll();
		Money festivalrent=rent;
		Money festivalbalance=balance.toList().get(0).getBalance();
		festivalbalance.subtract(festivalrent);
		return festivalbalance;

	}

	public Money payArtists(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		LineUp lineup = festivalManagement.getLineUpManagement().findById(festivalId);
		Streamable<Finance> balance=financeRepository.findAll();
		lineup.getBands();
		Money festivalbalance=balance.toList().get(0).getBalance();
		Money price=Money.of(0,EURO);
		for(Band bands:lineup.getBands()){
			price.add(bands.getPrice());
		}
		festivalbalance.subtract(price);
		return festivalbalance;
	}
	/* Dem Staff wird nie ein Preis zugeschrieben

	public Money payStaff(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		User user = festivalManagement.getUserManagement().findById(festivalId);
		Streamable<Finance> balance= financeRepository.findAll();
		Money festivalbalance=balance.toList().get(0).getBalance();
		Money salery=Money.of(0,EURO);
		for(User user:){
			salery.add(user.getSalery);
		}
		festivalbalance.subtract(salery);
		return festivalbalance;
	} */

	public Money payItems(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		Streamable<Food> items = festivalManagement.getCateringManagement().getCatalog(festivalId);
		Streamable<Finance> balance= financeRepository.findAll();
		Money festivalbalance=balance.toList().get(0).getBalance();
		Money price = Money.of(0,EURO);
		for(Food food: items){price.add(food.getPrice());}
		festivalbalance.subtract(price);
		return festivalbalance;

	}

	public void earnTicketSales(long festivalId){}

	public void earnCateringSales(long festivalId){}

	public Money festivaltotal(long festivalId){
		Festival festival = festivalManagement.findById(festivalId);
		Streamable<Finance> balance= financeRepository.findAll();
		Money festivalbalance=balance.toList().get(0).getBalance();
		festivalbalance.add(payLocation(festivalId)).add(payArtists(festivalId)).subtract(payItems(festivalId));
		return festivalbalance;
	}

	public Streamable<Finance> findAllFinances(){return financeRepository.findAll();}
}
