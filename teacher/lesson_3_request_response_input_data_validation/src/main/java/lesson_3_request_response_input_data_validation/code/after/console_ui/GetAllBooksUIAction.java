package lesson_3_request_response_input_data_validation.code.after.console_ui;

import lesson_3_request_response_input_data_validation.code.after.core.requests.GetAllBooksRequest;
import lesson_3_request_response_input_data_validation.code.after.core.responses.GetAllBooksResponse;
import lesson_3_request_response_input_data_validation.code.after.core.services.GetAllBooksService;

public class GetAllBooksUIAction implements UIAction {

	private GetAllBooksService getAllBooksService;

	public GetAllBooksUIAction(GetAllBooksService getAllBooksService) {
		this.getAllBooksService = getAllBooksService;
	}

	@Override
	public void execute() {
		System.out.println("Book list: ");
		GetAllBooksRequest request = new GetAllBooksRequest();
		GetAllBooksResponse response = getAllBooksService.execute(request);
		response.getBooks().forEach(System.out::println);
		System.out.println("Book list end.");
	}
}