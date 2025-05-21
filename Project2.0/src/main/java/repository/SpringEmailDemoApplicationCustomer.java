package repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Comparator;

@SpringBootApplication
public class SpringEmailDemoApplicationCustomer implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private ParcelRepository parcelRepository;

	@Autowired
	private EmailSenderServiceCustomer emailSenderService;

	public static void main(String[] args) {
		SpringApplication.run(SpringEmailDemoApplicationCustomer.class, args);
	}

	@Override
	public void run(String... args) {

		// Fetch the first parcel from the database
		parcel p = parcelRepository.findAll().stream()
				.max(Comparator.comparing(parcel::getParcelId))
				.orElse(null);


		if (p == null) {
			System.out.println("❌ No parcel found in the database.");
			return;
		}

		System.out.println("📦 Found parcel: " + p.getParcelId() + ", senderId: " + p.getSenderId());

		// Fetch the sender (customer)
		customer sender = customerRepository.findByCustomerId(p.getSenderId());

		if (sender == null) {
			System.out.println("❌ Customer not found for senderId: " + p.getSenderId());
			return;
		}

		// Send the email
		emailSenderService.sendShipmentEmail(
				sender.getEmail(),
				sender.getName(),
				p.getParcelId(),
				p.getStatus()
		);

		System.out.println("✅ Email sent to: " + sender.getEmail());
	}
}
