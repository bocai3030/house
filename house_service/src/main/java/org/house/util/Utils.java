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

	public static int tryParseInteger(final String str) {
		try {
			return Integer.parseInt(str);
		} catch (final Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
}
