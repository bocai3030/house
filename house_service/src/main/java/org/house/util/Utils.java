package org.house.util;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

public class Utils {
	public static void writlnAndFlushResponse(final HttpServletResponse response, final String string) {
		try {
			final PrintWriter printWriter = response.getWriter();
			printWriter.write(string);
			printWriter.write("<br/>");
			printWriter.flush();
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
}
