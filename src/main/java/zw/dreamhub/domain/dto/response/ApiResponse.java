package zw.dreamhub.domain.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/12/2022
 */

@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean successful;
    private Object message;
}
