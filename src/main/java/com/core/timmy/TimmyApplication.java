package com.core.timmy;

import java.time.LocalDate;
import java.util.Set;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import com.core.timmy.data.repository.IBudgetRepository;
import com.core.timmy.data.repository.IComunicationRepository;
import com.core.timmy.data.repository.IContactRepository;
import com.core.timmy.data.repository.ICustomerRepository;
import com.core.timmy.data.repository.IProviderRepository;
import com.core.timmy.data.repository.IRoleRepository;
import com.core.timmy.data.repository.IStatusRepository;
import com.core.timmy.data.repository.IUserRepository;
import com.core.timmy.data.model.User;
import com.core.timmy.data.model.Budget;
import com.core.timmy.data.model.Comunication;
import com.core.timmy.data.model.Customer;
import com.core.timmy.data.model.Contact;
import com.core.timmy.data.model.Provider;
import com.core.timmy.data.model.Role;
import com.core.timmy.data.model.Status;

@SpringBootApplication
public class TimmyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(TimmyApplication.class, args);
	}

	@Bean
	CommandLineRunner runner( // es como que si estuvieramos usando la consola

			IUserRepository userRepository, IRoleRepository roleRepository, IBudgetRepository budgetRepository,
			IComunicationRepository comunicationRepository, ICustomerRepository customerRepository,
			IContactRepository customerContactRepository, IStatusRepository statusRepository, 
			IProviderRepository providerRepository

	) {

		return args -> { // esta es una funcion anónima en java

			roleRepository.save(new Role("ADMIN")); // abria que agregar la colleccion en caso de que esto sea
													// bidireccional
			roleRepository.save(new Role("USER"));
			roleRepository.save(new Role("MANAGER"));
			// poner las contraseñas codificadas. El servidor de applicaciones es quien
			// compara el password y la codificación
			userRepository.save(new User("ana", "ana@gmail.com",
					"$2a$12$lNnVqBXnZjl3C9a8dnBkqurWXtMg0pOjQqYFlD76XJhF0.Wio6XXq"/* anaPass */, "Ana Perez",
					LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
					Set.of(roleRepository.findById("MANAGER").get(), roleRepository.findById("USER").get())));

			userRepository.save(new User("luis", "luis@gmail.com",
					"$2a$12$V7GPq65lUeKLc.sFDPUhi.OUAg9uMWQJvxvXSgXsj/te/pucKozzS"/* luisPass */, "luis Gomez",
					LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false, 
					Set.of(roleRepository.findById("USER").get())));// le
																														// hemos
																														// dado
																														// una
																														// coleccion
																														// vacia

			userRepository.save(new User("evano", "evano@gmail.com",
					"$2a$12$AD4HVFERi3n44CtWt4q0aeSQI1.yIQvXhM0./d4dnhMxtw2XQmGaO"/* evanoPass */, "Evano No",
					LocalDate.now().plusMonths(1), LocalDate.now().plusMonths(1), true, false,
					Set.of(roleRepository.findById("MANAGER").get())));

			// Lo que guardamos no es un estring sino el objeto entero por eso no puede solo
			// ponerse el nombre y se tiene que usar new para
			// crear una nueva instancia de Status
			statusRepository.save(new Status(null,"INICIADO"));
			statusRepository.save(new Status(null,"RECHAZADO"));
			statusRepository.save(new Status(null,"ENVIADO"));
			statusRepository.save(new Status(null,"ACEPTADO"));

			// si queremos asignar a un campo datos autoincrementados en la base de datos,
			// le pasamos un nulo o 0.
			customerRepository.save(new Customer(null, "TecnoVisión S.A.","A12345678", "ESB12345678",
					"Calle Innovación 12, Madrid, España", "+34 911 234 567", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Soluciones Globales SL", "A12345678", "ESB87654321",
					"Av. Empresarial 45, Barcelona, España", "+34 931 987 654", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "EcoEnergía Renovable", "A12345678", "ESA45678912",
					"Paseo Verde 3, Valencia, España", "+34 961 123 987", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Digital Innovación SL", "A12345678", "ESB34567890",
					"Calle de la Tecnología 22, Sevilla, España", "+34 954 321 654", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Alimentos Naturales SL", "A12345678", "ESA98765432",
					"Plaza Mercado Central 15, Zaragoza, España", "+34 976 567 123", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Transportes Rápidos SA", "A12345678", "ESB23456789",
					"Carretera Logística 87, Bilbao, España", "+34 944 765 432", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Diseño Creativo SL", "A12345678", "ESA67891234",
					"Avenida Artistas 9, Málaga, España", "+34 952 231 765", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Construcciones Modernas SL", "A12345678", "ESB19283746",
					"Calle del Progreso 18, Murcia, España", "+34 968 123 789", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Tecnología Futuro SL", "A12345678", "ESB56473829",
					"Ronda Innovación 44, Salamanca, España", "+34 923 432 876", "parapaloco@gmail.com", null, "15615645"));
			customerRepository.save(new Customer(null, "Café Aromático SL", "A12345678", "ESA12398765",
					"Plaza Central 7, Vigo, España", "+34 986 876 543", "parapaloco@gmail.com", null, "15615645"));
			//customer test when he has no contacst
			customerRepository.save(new Customer(null,"Retorno retorno retorno", "A12345678", "ESA12398765",
					"ñandu 235", "+34 986 876 563", "parapaloco@gmail.com", null, "15615645"));
                  /*es importante poner detras del numbero la "L" para que sepa que es un long*/
			customerContactRepository.save(new Contact(1L, "Carlos Martínez", "+34 600 123 456",
					"cmartinez@tecnovision.com", "Responsable de ventas", customerRepository.findById(1L).get(),null));
			customerContactRepository.save(new Contact(2L, "Ana Pérezosa", "+34 600 654 321",
					"aperez@tecnovision.com", "Encargada de proyectos", customerRepository.findById(1l).get(),null));
			customerContactRepository.save(new Contact(3L, "Javier Gómez", "+34 600 987 654",
					"jgomez@tecnovision.com", "Director de TI", customerRepository.findById(1L).get(),null));

			// Contactos para Soluciones Globales SL
			customerContactRepository.save(
					new Contact(4L, "Lucía Fernández", "+34 611 234 567", "lfernandez@solucionesglobales.com",
							"Asistente de dirección", customerRepository.findById(2L).get(),null));
			customerContactRepository.save(new Contact(5L, "Pedro Sánchez", "+34 611 765 432",
					"psanchez@solucionesglobales.com", "Jefe de operaciones", customerRepository.findById(2L).get(),null));
			customerContactRepository.save(new Contact(6L, "María López", "+34 611 876 543",
					"mlopez@solucionesglobales.com", "Departamento financiero", customerRepository.findById(2L).get(),null));

			// Contactos para EcoEnergía Renovable
			customerContactRepository.save(new Contact(7L, "Sergio Álvarez", "+34 622 123 987",
					"salvarez@ecoenergia.com", "Consultor técnico", customerRepository.findById(3L).get(),null));
			customerContactRepository.save(new Contact(8L, "Laura Ramos", "+34 622 654 321",
					"lramos@ecoenergia.com", "Ingeniera ambiental", customerRepository.findById(3L).get(),null));
			customerContactRepository.save(new Contact(9L, "Elena García", "+34 622 789 654",
					"egarcia@ecoenergia.com", "Gerente de proyectos", customerRepository.findById(3L).get(),null));

			// Contactos para Digital Innovación SL
			customerContactRepository.save(new Contact(10L, "Pablo Torres", "+34 633 321 654",
					"ptorres@digitalinnovacion.com", "CEO", customerRepository.findById(4L).get(),null));
			customerContactRepository
					.save(new Contact(11L, "Raquel Jiménez", "+34 633 432 765", "rjimenez@digitalinnovacion.com",
							"Coordinadora de marketing", customerRepository.findById(4L).get(),null));
			customerContactRepository.save(new Contact(12L, "José Ruizes", "+34 633 543 876",
					"jruiz@digitalinnovacion.com", "Desarrollador de software", customerRepository.findById(4L).get(),null));
			customerContactRepository.save(new Contact(13L, "Verónica Sáez", "+34 633 654 987",
					"vsaez@digitalinnovacion.com", "Atención al cliente", customerRepository.findById(4L).get(),null));

			// Contactos para Alimentos Naturales SL
			customerContactRepository.save(new Contact(14L, "Isabel Cano", "+34 644 567 123",
					"icano@alimentosnaturales.com", "Encargada de compras", customerRepository.findById(5L).get(),null));
			customerContactRepository.save(new Contact(15L, "Ricardo Castro", "+34 644 678 234",
					"rcastro@alimentosnaturales.com", "Gerente de distribución", customerRepository.findById(5L).get(),null));
			customerContactRepository.save(new Contact(16L, "Marta Vegass", "+34 644 789 345",
					"mvega@alimentosnaturales.com", "Responsable de calidad", customerRepository.findById(5L).get(),null));

			// Contactos para Transportes Rápidos SA
			customerContactRepository.save(new Contact(17L, "Héctor Navarro", "+34 655 765 432",
					"hnavarro@transportesrapidos.com", "Jefe de logística", customerRepository.findById(6L).get(),null));
			customerContactRepository.save(new Contact(18L, "Sonia Blanco", "+34 655 876 543",
					"sblanco@transportesrapidos.com", "Encargada de tráfico", customerRepository.findById(6L).get(),null));
			customerContactRepository.save(new Contact(19L, "Luis Moreno", "+34 655 987 654",
					"lmoreno@transportesrapidos.com", "Supervisor de rutas", customerRepository.findById(6L).get(),null));

			// Contactos para Diseño Creativo SL
			customerContactRepository.save(new Contact(20L, "Alberto Díaz", "+34 666 231 765",
					"adiaz@diseñocreativo.com", "Director de arte", customerRepository.findById(7L).get(),null));
			customerContactRepository.save(new Contact(21L, "Patricia Mena", "+34 666 342 876",
					"pmena@diseñocreativo.com", "Ilustradora", customerRepository.findById(7L).get(),null));
			customerContactRepository.save(new Contact(22L, "Jorge Ortega", "+34 666 453 987",
					"jortega@diseñocreativo.com", "Diseñador gráfico", customerRepository.findById(7L).get(),null));

			// Contactos para Construcciones Modernas SL
			customerContactRepository.save(new Contact(23L, "Miguel Serrano", "+34 677 123 789",
					"mserrano@construccionesmodernas.com", "Arquitecto jefe", customerRepository.findById(8L).get(),null));
			customerContactRepository.save(new Contact(24L, "Andrea Medina", "+34 677 234 890",
					"amedina@construccionesmodernas.com", "Jefa de obra", customerRepository.findById(8L).get(),null));
			customerContactRepository.save(new Contact(25L, "Víctor Gómez", "+34 677 345 901",
					"vgomez@construccionesmodernas.com", "Ingeniero civil", customerRepository.findById(8L).get(),null));

			// Contactos para Tecnología Futuro SL
			customerContactRepository.save(new Contact(26L, "David Romero", "+34 688 432 876",
					"dromero@tecnologiafuturo.com", "Desarrollador senior", customerRepository.findById(9L).get(),null));
			customerContactRepository.save(new Contact(27L, "Beatriz González", "+34 688 543 987",
					"bgonzalez@tecnologiafuturo.com", "Gerente de producto", customerRepository.findById(9L).get(),null));
			customerContactRepository.save(new Contact(28L, "Carlos Vázquez", "+34 688 654 098",
					"cvazquez@tecnologiafuturo.com", "Analista de datos", customerRepository.findById(9L).get(),null));

			// Contactos para Café Aromático SL
			customerContactRepository.save(new Contact(29L, "Sofía Morales", "+34 699 876 543",
					"smorales@cafearomatico.com", "Encargada de marketing", customerRepository.findById(10L).get(),null));
			customerContactRepository.save(new Contact(30L, "Fernando Rubio", "+34 699 987 654",
					"frubio@cafearomatico.com", "Director de ventas", customerRepository.findById(10L).get(),null));
			customerContactRepository.save(new Contact(31L, "Natalia Castro", "+34 699 123 456",
					"ncastro@cafearomatico.com", "Coordinadora de logística", customerRepository.findById(10L).get(),null));

			budgetRepository.save(new Budget(null, 15000.50, statusRepository.findById(1L).get(), null));
			budgetRepository.save(new Budget(null, 8500.00, statusRepository.findById(3L).get(), null));
			budgetRepository.save(new Budget(null, 12000.75, statusRepository.findById(4L).get(), null));
			budgetRepository.save(new Budget(null, 6000.00, statusRepository.findById(2L).get(), null));
			budgetRepository.save(new Budget(null, 20000.00, statusRepository.findById(1L).get(), null));
			budgetRepository.save(new Budget(null, 9500.00, statusRepository.findById(3L).get(), null));
			budgetRepository.save(new Budget(null, 18000.00, statusRepository.findById(4L).get(), null));
			budgetRepository.save(new Budget(null, 7000.00, statusRepository.findById(2L).get(), null));
			budgetRepository.save(new Budget(null, 25000.00, statusRepository.findById(1L).get(), null));
			budgetRepository.save(new Budget(null, 14000.50, statusRepository.findById(3L).get(), null));

			// null el primer parámetro porque va a ser auto-incrementado

			/*
			 * id budget status customer contact message message date amouunt
		 */
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(1L).get(),
					statusRepository.findById(1L).get(), customerContactRepository.findById(1L).get(),
					"Revisando detalles del presupuesto.", LocalDate.of(2024, 9, 20), 1500.50));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(2L).get(),
					statusRepository.findById(3L).get(), customerContactRepository.findById(4L).get(),
					"Pendiente de aprobación por el equipo financiero.", LocalDate.parse("2024-09-23"), 8500.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(3L).get(),
					statusRepository.findById(4L).get(), customerContactRepository.findById(7L).get(),
					"Presupuesto aprobado. Adelante con el proyecto.", LocalDate.parse("2024-09-12"), 12000.75));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(4L).get(),
					statusRepository.findById(2L).get(), customerContactRepository.findById(10L).get(),
					"Presupuesto fuera del alcance del cliente.", LocalDate.parse("2024-09-06"), 6000.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(5L).get(),
					statusRepository.findById(1L).get(), customerContactRepository.findById(14L).get(),
					 "Hemos recibido el presupuesto, en revisión.", LocalDate.parse("2024-09-26"), 20000.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(6L).get(),
					statusRepository.findById(3L).get(), customerContactRepository.findById(17L).get(),
					"Esperando respuesta de su parte.", LocalDate.parse("2024-09-19"), 9500.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(7L).get(),
					statusRepository.findById(4L).get(), customerContactRepository.findById(20L).get(),
					"Todo listo para iniciar el proyecto.", LocalDate.parse("2024-09-21"), 18000.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(8L).get(),
					statusRepository.findById(2L).get(), customerContactRepository.findById(23L).get(),
					"No podemos proceder con el presupuesto.", LocalDate.parse("2024-09-09"), 18000.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(9L).get(),
					statusRepository.findById(1L).get(), customerContactRepository.findById(26L).get(),
					"En proceso de revisión por el equipo.", LocalDate.parse("2024-09-28"), 25000.00));
			
			comunicationRepository.save(new Comunication(null, budgetRepository.findById(10L).get(),
					statusRepository.findById(3L).get(), customerContactRepository.findById(29L).get(),
					"Presupuesto enviado, pendiente de revisión.", LocalDate.parse("2024-09-22"), 14000.50));
			
			
			providerRepository.save(new Provider(1L, "Tech Innovators", "CIF12345A","ESB98765432","Pez, 13", "600123456", "contact@techinnovators.com",null, "12345678Z"));
			providerRepository.save(new Provider(2L, "Green Solutions", "CIF12346B","ESB48765432","Pez, 13", "600234567", "info@greensolutions.com",null, "31109745Z"));
			providerRepository.save(new Provider(3L, "Logistics Plus", "CIF12347C","ESB98765432","Pez, 13", "600345678", "support@logisticsplus.com",null, "34184427X"));
			providerRepository.save(new Provider(4L, "Quantum Supplies", "CIF12348D","ESB98765432","Pez, 13", "600456789", "sales@quantumsupplies.com",null, "18005672F"));
			providerRepository.save(new Provider(5L, "Blue Ocean", "CIF12349E","ESB98765432","Pez, 13", "600567890", "contact@blueocean.com",null, "52811876N"));
			providerRepository.save(new Provider(6L, "Eco Future", "CIF12350F","ESB98765432","Pez, 13", "600678901", "hello@ecofuture.com",null, "64609646R"));
			providerRepository.save(new Provider(7L, "Bright Horizons", "CIF12351G","ESB98765432","Pez, 13", "600789012", "services@brighthorizons.com",null, "78714828L"));
			providerRepository.save(new Provider(8L, "Smart Goods", "CIF12352H","ESB98765432","Pez, 13", "600890123", "info@smartgoods.com",null, "25754313V"));
			providerRepository.save(new Provider(9L, "Urban Ventures", "CIF12353I","ESB98765432","Pez, 13", "600901234", "team@urbanventures.com",null, "00754132P"));
			providerRepository.save(new Provider(10L, "Prime Essentials", "CIF12354J","ESB98765432","Pez, 13", "601012345", "contact@primeessentials.com",null, "55956442H"));
			providerRepository.save(new Provider(11L, "Tech Innovators","CIF12454J","ESB98765432","Pez, 13", "600123456","papachengo@perrisimos.com",null, "99490643A"));


			

		};

	}

}
