
package services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DashboardRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class DashboardService {

	@Autowired
	DashboardRepository	dashboardRepository;


	private boolean checkAdmin() {
		final Authority a = new Authority();
		a.setAuthority(Authority.ADMIN);
		final UserAccount user = LoginService.getPrincipal();
		return user.getAuthorities().contains(a);
	}

	public Double avgSubmissions() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgSubmissions();
	}

	public Integer minSubmissions() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minSubmissions();
	}

	public Integer maxSubmissions() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxSubmissions();
	}

	public Double stddevSubmissions() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevSubmissions();
	}

	public Double avgRegistrations() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgRegistrations();
	}

	public Integer minRegistrations() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minRegistrations();
	}

	public Integer maxRegistrations() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxRegistrations();
	}

	public Double stddevRegistrations() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevRegistrations();
	}

	public Double avgFee() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgFee();
	}

	public Integer minFee() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minFee();
	}

	public Integer maxFee() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxFee();
	}

	public Double stddevFee() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevFee();
	}

	public List<Double> days() {
		final List<Date> startDates = this.dashboardRepository.startDates();
		final List<Date> endDates = this.dashboardRepository.endDates();
		final List<Double> days = new ArrayList<>();
		for (int i = 0; i < startDates.size(); i++) {
			final long diffInMillies = Math.abs(endDates.get(i).getTime() - startDates.get(i).getTime());
			final double diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
			days.add(diff);
		}
		return days;
	}

	public Double avgDays(final List<Double> days) {
		Double sum = 0.0;
		if (!days.isEmpty()) {
			for (final Double d : days)
				sum += d;
			return sum.doubleValue() / days.size();
		}
		return sum;
	}

	public Double minDays(final List<Double> days) {
		Double min = days.get(0);
		for (int i = 1; i < days.size(); i++)
			min = Math.min(days.get(i), min);
		return min;
	}

	public Double maxDays(final List<Double> days) {
		Double max = days.get(0);
		for (int i = 1; i < days.size(); i++)
			max = Math.max(days.get(i), max);
		return max;
	}

	public Double stddevDays(final List<Double> days) {
		Double mean = 0.0;

		for (int i = 0; i < days.size(); i++) {
			final Double currentNum = days.get(i);
			mean += currentNum;
		}
		mean = mean / days.size();
		double temp = 0;

		for (int i = 0; i < days.size(); i++) {
			final Double val = days.get(i);
			final double squrDiffToMean = Math.pow(val - mean, 2);
			temp += squrDiffToMean;
		}
		final double meanOfDiffs = temp / (days.size());
		return Math.sqrt(meanOfDiffs);
	}

	public Double avgCategory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgCategory();
	}

	public Integer minCategory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minCategory();
	}

	public Integer maxCategory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxCategory();
	}

	public Double stddevCategory() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevCategory();
	}

	public Double avgCommentsConf() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgCommentsConf();
	}

	public Integer minCommentsConf() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minCommentsConf();
	}

	public Integer maxCommentsConf() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxCommentsConf();
	}

	public Double stddevCommentsConf() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevCommentsConf();
	}

	public Double avgCommentsAct() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.avgCommentsAct();
	}

	public Integer minCommentsAct() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.minCommentsAct();
	}

	public Integer maxCommentsAct() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.maxCommentsAct();
	}

	public Double stddevCommentsAct() {
		Assert.isTrue(this.checkAdmin());
		return this.dashboardRepository.stddevCommentsAct();
	}

}
