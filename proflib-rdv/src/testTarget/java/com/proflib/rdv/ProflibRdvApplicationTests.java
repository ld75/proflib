package com.proflib.rdv;

import com.proflib.rdv.datasource.DatasourceMockConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes={DatasourceMockConfig.class}) // pour que la classe de test ne charge que la configuration qui mock la datasource
class ProflibRdvApplicationTests {

	@Test
	void contextLoads() {
	}

}
