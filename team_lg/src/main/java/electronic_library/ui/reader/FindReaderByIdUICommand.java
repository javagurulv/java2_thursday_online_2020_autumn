package electronic_library.ui.reader;

import electronic_library.core.domain.Reader;
import electronic_library.core.requests.reader.FindReaderByIdRequest;
import electronic_library.core.responses.reader.FindReaderByIdResponse;
import electronic_library.core.services.reader.FindReaderByIdService;
import electronic_library.ui.UICommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class FindReaderByIdUICommand implements UICommand {

    @Autowired
    private FindReaderByIdService findReaderByIdService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter Reader ID: ");
        String id = scanner.nextLine();

        FindReaderByIdRequest request = new FindReaderByIdRequest(id);
        FindReaderByIdResponse response = findReaderByIdService.findReaderByIdResponse(request);

        Optional<Reader> findReader = response.getFindReaderById();

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getErrorField() + " " + coreError.getErrorMessage()));
        } else {
            if (response.getFindReaderById().isEmpty()) {
                System.out.println("\nNo reader with ID=" + id + " in Reader's registry.");
            } else {
                Reader reader = findReader.get();
                System.out.println("Found reader in the Reader's registry :");
                System.out.print(reader.toString() + "\n");
            }
        }
    }
}
