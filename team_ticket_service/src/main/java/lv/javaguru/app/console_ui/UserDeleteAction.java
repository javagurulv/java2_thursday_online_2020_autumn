package lv.javaguru.app.console_ui;

import lv.javaguru.app.core.common.BaseFunc;
import lv.javaguru.app.core.request.UserDeleteRequest;
import lv.javaguru.app.core.response.UserDeleteResponse;
import lv.javaguru.app.core.services.UserDeleteService;

public class UserDeleteAction {

	private final UserDeleteService userDeleteService;

	public UserDeleteAction (UserDeleteService userDeleteService) {
		this.userDeleteService = userDeleteService;
	}

	public void execute () {
		BaseFunc.printHeader("Enter user ID:");
		long id = BaseFunc.getMenuNumberFromUser();

		UserDeleteRequest request = new UserDeleteRequest(id);
		UserDeleteResponse response = userDeleteService.execute(request);

		if (response.hasError())
			System.out.println(response.getError());
		else if (response.isDeleted())
			System.out.println("User deleted!");
		else
			System.out.println("Failed to delete!");

	}
}
