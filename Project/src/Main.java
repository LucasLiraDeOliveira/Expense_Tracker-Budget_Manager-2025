import repository.ExpenseRepository;
import service.ExpenseService;
import ui.ConsoleMenu;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ExpenseRepository repository = new ExpenseRepository();
        ExpenseService service = new ExpenseService(repository);
        ConsoleMenu menu = new ConsoleMenu(service);
    }
}