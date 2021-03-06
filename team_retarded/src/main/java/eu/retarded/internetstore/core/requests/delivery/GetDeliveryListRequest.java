package eu.retarded.internetstore.core.requests.delivery;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Getter
@AllArgsConstructor
@NoArgsConstructor
public class GetDeliveryListRequest {

    private Pageable pageable;
}
