package internet_store.integration.telegram.service;

import internet_store.integration.telegram.ChatBot;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Service
public class UploadService {
    @Autowired
    private ChatBot chatBot;
    private boolean isSuccessful;

    public void uploadFile(String filePath, long chatId) throws IOException {
        String url = "https://api.telegram.org/bot" + chatBot.getBotToken() + "/sendDocument" +
                "?chat_id=" + chatId;

        OkHttpClient client = new OkHttpClient();

        File sourceFile = new File(filePath);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("document", sourceFile.getName(),
                        RequestBody.create(sourceFile, MediaType.parse("application/pdf")))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        Response response = client.newCall(request).execute();
        isSuccessful = response.isSuccessful();
        Objects.requireNonNull(response.body()).close();
    }

    public boolean isSuccessful() {
        return isSuccessful;
    }
}