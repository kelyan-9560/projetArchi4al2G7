package TradesMan;

import cc2.use_cases.tradesman.domain.*;
import cc2.use_cases.tradesman.infrastructure.InMemoryTradesManRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(MockitoExtension.class)
public class TradesManControllerTest {

    private MockMvc mvc;

    @Mock
    private InMemoryTradesManRepository tradesManRepository;



    private JacksonTester<TradesMan> jacksonTester;



}
