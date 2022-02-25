package com.hilos.futuretask.logic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Scope
@Service
public class FutureTaskAction {

	public String prueba(boolean sleep, int seconds, boolean repeat, int count, int err, String msn) {
		return executeFutureTask(sleep, seconds, repeat, count, err, msn);
	}

	private String executeFutureTask(boolean sleep, int seconds, boolean repeat, int count, int err, String msn) {

		String response = null;

		do {

			ExecutorService executor = Executors.newSingleThreadExecutor();

			FutureTask<String> ft = new FutureTask<>(() -> {
				if (sleep) {
					Thread.sleep((long) seconds*1000);
				}

				return "done: " + msn;
			});

			executor.submit(ft);

			try {
				response = ft.get(err == 1 ? seconds/2 : seconds, TimeUnit.SECONDS);
			} catch (InterruptedException ie) {
				Thread.currentThread().interrupt();
				response = "No se obtuvo la respuesta del servicio, ERROR IE: " + ie.getMessage();
			} catch(TimeoutException te) {
				response = "No se obtuvo la respuesta del servicio, ERROR TE: TIMEOUT";
			} catch(Exception e) {
				response = "No se obtuvo la respuesta del servicio, ERROR E: " + e.getMessage();
			} finally {
				executor.shutdown();
				executor.shutdownNow();
			}

			count--;

		} while((repeat && count > 0));

		return response;
	}
}
