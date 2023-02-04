package Day27_0121_Praktinis_darbas;


import Day27_0121_Praktinis_darbas.entity.Customer;
import Day27_0121_Praktinis_darbas.entity.Project;
import Day27_0121_Praktinis_darbas.repository.*;
import Day27_0121_Praktinis_darbas.service.CustomerInfoService;
import Day27_0121_Praktinis_darbas.service.DataMapperService;
import Day27_0121_Praktinis_darbas.service.JsonService;
import Day27_0121_Praktinis_darbas.service.ProjectInfoService;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {

        CustomerRepo customerRepo = new CustomerRepo();

        JsonService jsonService = new JsonService();
        List<Customer> customersList = jsonService.fileToList();

        DataMapperService dataMapperService = new DataMapperService();
        List<Customer> mappedList = dataMapperService.mapsListFromJson(customersList);
        customerRepo.saveList(mappedList);

        Customer newCustomer = jsonService.createsOneCustomerFromFile();
        List<Customer> singleCustomer = dataMapperService.mapsListFromJson(Collections.singletonList(newCustomer));
        customerRepo.save(singleCustomer.get(0));

        List<Customer> customersFromDB = customerRepo.findAll();
        //customersFromDB.get(1).setName("Martyno");
        //customerRepo.update(customersFromDB.get(1));
        //customerRepo.delete(customersFromDB.get(2));

        CustomerInfoService customerInfoService = new CustomerInfoService();
        System.out.println("------Customer with most projects-----");
        customerInfoService.findWithMostProjects(customersFromDB).forEach(System.out::println);
        System.out.println("------Customer with most employees-----");
        customerInfoService.findWithMostEmployees(customersFromDB).forEach(System.out::println);
        System.out.println("------Customer with most income-----");
        customerInfoService.findWithMostIncome(customersFromDB).forEach(System.out::println);
        System.out.println("------Customer with most expenses-----");
        customerInfoService.findWithMostCosts(customersFromDB).forEach(System.out::println);
        System.out.println("------Customer with most profit-----");
        customerInfoService.findMostProfitable(customersFromDB).forEach(System.out::println);
        System.out.println("------Customer with least profit-----");
        customerInfoService.findLeastProfitable(customersFromDB).forEach(System.out::println);

        ProjectRepo projectRepo = new ProjectRepo();
//        projectRepo.delete(customersFromDB.get(1).getProjects().get(0));
        List<Project> projectsList = projectRepo.findAll();


        ProjectInfoService projectInfoService = new ProjectInfoService();
        System.out.println("------Projects with most employees-----");
        projectInfoService.findWithMostEmployees(projectsList).forEach(System.out::println);
        System.out.println("------Projects with most income-----");
        projectInfoService.findWithMostIncome(projectsList).forEach(System.out::println);
        System.out.println("------Projects with the biggest costs-----");
        projectInfoService.findWithTheBiggestCosts(projectsList).forEach(System.out::println);
        System.out.println("------Projects with the biggest employee average generated income-----");
        projectInfoService.findWithBiggestGeneratedEmployeeValue(projectsList).forEach(System.out::println);
        System.out.println("------Projects with biggest profit-----");
        projectInfoService.findWithMostProfit(projectsList).forEach(System.out::println);
        System.out.println("------Projects with longest work time-----");
        projectInfoService.findLongest(projectsList).forEach(System.out::println);
        System.out.println("------Projects with shortest work time-----");
        projectInfoService.findLShortest(projectsList).forEach(System.out::println);

    }
}
