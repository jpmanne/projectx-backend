package com.at.projx;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.context.WebApplicationContext;

//https://www.javainuse.com/spring/springboot_testcases

public class TestTicketController extends ProjectXApplicationTests {

	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	// ========================================================================

	/*
	 * @Test public void testGetTicketByUser() throws Exception {
	 * LinkedMultiValueMap<String, String> requestParams = new
	 * LinkedMultiValueMap<>(); requestParams.add("authCode",
	 * "dFu2xOb5uRs61574245514859"); //requestParams.add("name", "john");
	 * //requestParams.add("age", "30");
	 * 
	 * //https://stackoverflow.com/questions/17972428/mock-mvc-add-request-parameter
	 * -to-test/17985015 mockMvc.perform(get("/api/ticket/user/2")
	 * .params(requestParams)) .andDo(print()) .andExpect(status().isOk())
	 * .andExpect(content().contentType("application/json;charset=UTF-8"))
	 * .andExpect(jsonPath("$.message").value("Tickets"))
	 * .andExpect(jsonPath("$.userDetailsId").value(2)) ; //
	 * .andExpect(jsonPath("$.empId").value("1")).andExpect(jsonPath("$.salary").
	 * value(3000)); }
	 */
	// ========================================================================

	// https://stackoverflow.com/questions/51346781/how-to-test-post-method-in-spring-boot-using-mockito-and-junit
	/*
	 * @Test public void testAddTicket() throws Exception {
	 * LinkedMultiValueMap<String, String> requestParams = new
	 * LinkedMultiValueMap<>(); requestParams.add("authCode",
	 * "dFu2xOb5uRs61574245514859");
	 * 
	 * TicketDetails ticketDetails = new TicketDetails();
	 * ticketDetails.setCreatedAt(new Date()); ticketDetails.setStatus("1");
	 * ticketDetails.setTicketDescription("This is JUnit Test Ticket Description");
	 * ticketDetails.setTicketNo("JUNIT-0001"); ticketDetails.setUserDetailsId(2L);
	 * 
	 * 
	 * mockMvc.perform(post("/api/ticket/add")
	 * .contentType(MediaType.APPLICATION_JSON)
	 * .content(asJsonString(ticketDetails)) .params(requestParams)) .andDo(print())
	 * .andExpect(status().isOk());
	 * 
	 * }
	 */

	// ========================================================================

	/*
	 * public static String asJsonString(final Object obj) { try { return new
	 * ObjectMapper().writeValueAsString(obj); } catch (Exception e) { throw new
	 * RuntimeException(e); } }
	 */

	// ========================================================================
}
