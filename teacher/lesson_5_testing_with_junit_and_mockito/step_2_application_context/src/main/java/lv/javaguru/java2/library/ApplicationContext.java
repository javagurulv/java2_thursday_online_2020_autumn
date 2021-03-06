package lv.javaguru.java2.library;

import java.util.HashMap;
import java.util.Map;

import lv.javaguru.java2.library.console_ui.AddBookUIAction;
import lv.javaguru.java2.library.console_ui.ExitUIAction;
import lv.javaguru.java2.library.console_ui.GetAllBooksUIAction;
import lv.javaguru.java2.library.console_ui.RemoveBookUIAction;
import lv.javaguru.java2.library.console_ui.SearchBooksUIAction;
import lv.javaguru.java2.library.console_ui.UIAction;
import lv.javaguru.java2.library.core.database.Database;
import lv.javaguru.java2.library.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.library.core.services.AddBookService;
import lv.javaguru.java2.library.core.services.GetAllBooksService;
import lv.javaguru.java2.library.core.services.RemoveBookService;
import lv.javaguru.java2.library.core.services.SearchBooksService;
import lv.javaguru.java2.library.core.services.validators.AddBookRequestValidator;
import lv.javaguru.java2.library.core.services.validators.RemoveBookRequestValidator;
import lv.javaguru.java2.library.core.services.validators.SearchBooksRequestValidator;

public class ApplicationContext {

	private Map<Class, Object> beans = new HashMap<>();

	public ApplicationContext() {
		beans.put(Database.class, new InMemoryDatabaseImpl());

		beans.put(AddBookRequestValidator.class, new AddBookRequestValidator());
		beans.put(RemoveBookRequestValidator.class, new RemoveBookRequestValidator());
		beans.put(SearchBooksRequestValidator.class, new SearchBooksRequestValidator());

		beans.put(AddBookService.class, new AddBookService(
				getBean(Database.class),
				getBean(AddBookRequestValidator.class)));
		beans.put(RemoveBookService.class, new RemoveBookService(
				getBean(Database.class),
				getBean(RemoveBookRequestValidator.class)));
		beans.put(GetAllBooksService.class, new GetAllBooksService(getBean(Database.class)));
		beans.put(SearchBooksService.class, new SearchBooksService(
				getBean(Database.class),
				getBean(SearchBooksRequestValidator.class)));

		beans.put(AddBookUIAction.class, new AddBookUIAction(getBean(AddBookService.class)));
		beans.put(RemoveBookUIAction.class, new RemoveBookUIAction(getBean(RemoveBookService.class)));
		beans.put(GetAllBooksUIAction.class, new GetAllBooksUIAction(getBean(GetAllBooksService.class)));
		beans.put(ExitUIAction.class, new ExitUIAction());
		beans.put(SearchBooksUIAction.class, new SearchBooksUIAction(getBean(SearchBooksService.class)));
	}

	public <T extends Object> T getBean(Class c) {
		return (T) beans.get(c);
	}

}