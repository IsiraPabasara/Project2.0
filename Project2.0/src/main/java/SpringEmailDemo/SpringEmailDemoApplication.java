package SpringEmailDemo;

import SpringEmailDemo.model.DeliveryPersonnel;
import SpringEmailDemo.model.DeliverySchedule;
import SpringEmailDemo.repository.DeliveryPersonnelRepository;
import SpringEmailDemo.repository.DeliveryScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Sort;

import java.util.List;

@SpringBootApplication
public class SpringEmailDemoApplication implements CommandLineRunner {

	@Autowired
	private DeliveryPersonnelRepository deliveryPersonnelRepository;

	@Autowired
	private DeliveryScheduleRepository deliveryScheduleRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplication.class, args);
	}

	@Override
	public void run(String... args) {

		// Get last DeliveryPersonnel
		List<DeliveryPersonnel> personnelList = deliveryPersonnelRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		// Get last DeliverySchedule
		List<DeliverySchedule> scheduleList = deliveryScheduleRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

		if (!personnelList.isEmpty() && !scheduleList.isEmpty()) {
			DeliveryPersonnel dp = personnelList.get(0);
			DeliverySchedule ds = scheduleList.get(0);

			StringBuilder scheduleDetails = new StringBuilder();
			scheduleDetails.append("\n\nHere is your latest delivery assignment:\n")
					.append("- Parcel ID: ").append(ds.getParcelId())
					.append("\n  Schedule Date: ").append(ds.getScheduleDate());


			String email = dp.getEmail();
			String subject = "Latest Delivery Assignment";
			String body = "Hello " + dp.getName() + ",\n\nYou have a new delivery parcel."
					+ scheduleDetails.toString()
					+ "\n\nRegards,\nDelivery Team";

			emailSenderService.sendEmail(email, subject, body);
			System.out.println("✅ Email sent to: " + email);
		} else {
			if (personnelList.isEmpty()) {
				System.out.println("❌ No DeliveryPersonnel records found.");
			}
			if (scheduleList.isEmpty()) {
				System.out.println("❌ No DeliverySchedule records found.");
			}
		}
	}
}