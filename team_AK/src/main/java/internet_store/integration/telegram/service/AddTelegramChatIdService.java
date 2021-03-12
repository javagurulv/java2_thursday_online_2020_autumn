package internet_store.integration.telegram.service;

import internet_store.core.domain.TelegramChatId;
import internet_store.core.persistence.TelegramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AddTelegramChatIdService {
    @Autowired
    private TelegramRepository telegramRepository;

    public void AddNewClientChatId(long chatId, String orderNumber) {
        TelegramChatId telegramChatId = new TelegramChatId();
        telegramChatId.setChatId(chatId);
        telegramChatId.setOrderNumber(orderNumber);

        telegramRepository.save(telegramChatId);
    }
}