/* Project Connect
 * Copyright (c) 2011 Deutsche Post DHL
 * All rights reserved.
 */

package com.foosball.web.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.foosball.web.service.EntityService;

/**
 * Returns JSON for foosball tournament bracket.
 *
 * @author rpasricha
 */
@Controller
public class FoosballController {
	
	@Resource
	private EntityService entityService;

    @RequestMapping(value = "/getfoosball.html", method = RequestMethod.GET)
    @ResponseBody
    public String getFoosball() {
    	
    	String resultJson = entityService.getFoosballScores();

    String json =
        "{\"teams\":[[\"Team 1\",\"Team 2\"],"+
                 "[\"Team 3\",\"Team 4\"],"+
                 "[\"Team 5\",\"Team 6\"],"+
                 "[\"Team 7\",\"Team 8\"],"+
                 "[\"Team 9\",\"Team 10\"],"+
                 "[\"Team 11\",\"Team 12\"],"+
                 "[\"Team 13\",\"Team 14\"],"+
                 "[\"Team 15\",\"Team 16\"]],"+
        "\"results\":[["+
                "[[null,null],[null,null],[null,null],[null,null],[null,null],[null,null],[null,null],[null,null]],"+
                "[[null,null],[null,null],[null,null],[null,null]],"+
                "[[null,null],[null,null]],"+
                "[[null,null]]"+
                "], ["+
                    "[[null,null],[null,null],[null,null],[null,null]],"+
                    "[[null,null],[null,null],[null,null],[null,null]],"+
                    "[[null,null],[null,null]],"+
                    "[[null,null],[null,null]],"+
                    "[[null,null]],"+
                    "[[null,null]]"+
                "], ["+
                    "[[null,null],[null,null]],"+
                    "[[null,null]]"+
                "]]}";

        return json;
    }

    @RequestMapping(value = "/postfoosball.html", method = RequestMethod.POST)
    @ResponseBody
    public Object postFoosball(@RequestBody String input) {
    	return "NOT_IMPLEMENTED";
    }

}
