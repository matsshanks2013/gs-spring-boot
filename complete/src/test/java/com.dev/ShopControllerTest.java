package com.dev;

/**
 * This class is under construction. Trying to execute JUNIT with Mockito as well.
 */

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.Charset;

import org.springframework.beans.factory.annotation.Autowired;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.dev.IShopService;
import com.dev.ShopRestController;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MockServletContext.class)
@WebAppConfiguration
public class ShopControllerTest {
	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	private final String EMPLOYEE_REQUEST = "{\"name\" : \"ename\"}";

	private MockMvc mockMvc;

	@Autowired
	private ShopRestController shopController;

	private MockMvc mvc;
	
    @Autowired
    private IShopService shopService;

	@Before
	public void setUp() throws Exception {
		mvc = MockMvcBuilders.standaloneSetup(new ShopRestController()).build();
	}

	@Test
	public void getShop() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Greetings from Spring Boot!")));
	}
//	
//	@Test
//    public void findById_Shop() throws Exception {
//        Todo found = new TodoBuilder()
//                .id(1L)
//                .description("Lorem ipsum")
//                .title("Foo")
//                .build();
// 
//        when(shopService.findById(1L)).thenReturn(found);
// 
//        mockMvc.perform(get("/api/todo/{id}", 1L))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8))
//                .andExpect(jsonPath("$.id", is(1)))
//                .andExpect(jsonPath("$.description", is("Lorem ipsum")))
//                .andExpect(jsonPath("$.title", is("Foo")));
// 
//        verify(todoServiceMock, times(1)).findById(1L);
//        verifyNoMoreInteractions(todoServiceMock);
//    }
	
//	@Test
//	public void shouldSaveEmployee() throws Exception {
//		this.mockMvc.perform(post("/employee/save/").content(EMPLOYEE_REQUEST)
//								.contentType(APPLICATION_JSON_UTF8))
//				.andExpect(status().isOk())
//				.andExpect(jsonPath("$id", is("e1")))
//				.andExpect(jsonPath("$name", is("ename")));
//	}
//	
//	@Test
//	public void shouldNotFindTheResource() throws Exception{
//		this.mockMvc
//		.perform(
//				post("/employee/store/").content(EMPLOYEE_REQUEST)
//						.contentType(APPLICATION_JSON_UTF8))
//		.andExpect(status().is(404));
//	}
}
