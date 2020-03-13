/**
* @author  Jaya Prakash Manne
* @version 1.0
* @since   23-Jan-2020 
*/
package com.at.projx.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.at.projx.common.URLConstants;

@RestController
@RequestMapping(URLConstants.Registration.API_BASE)
public class RegistrationController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);
	
	//=========================================================================
}
