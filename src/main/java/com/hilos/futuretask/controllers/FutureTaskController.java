package com.hilos.futuretask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hilos.futuretask.logic.FutureTaskAction;

@RestController
public class FutureTaskController {

	@Autowired
	private FutureTaskAction futureTaskAction;

	@GetMapping("/Saludo")
	public String prueba(@RequestParam("sleep") boolean sleep, @RequestParam("seconds") int seconds,
			@RequestParam("repeat") boolean repeat, @RequestParam("count") int count, @RequestParam("err") int err,
			@RequestParam("msn") String msn) {
		return futureTaskAction.prueba(sleep, seconds, repeat, count, err, msn);
	}
}
