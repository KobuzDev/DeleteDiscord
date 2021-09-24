package gg.kobuz.deletediscord.util;

import java.util.Date;

public class DateUtil {
	
	public static String dateFormat(Date date) {
		@SuppressWarnings("deprecation")
		String ret = date.getDay() + "/" + date.getMonth() + "/" + date.getYear() + " " + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();
		return ret;
	}
	
	public static String dateFormat2(Date date) {
		@SuppressWarnings("deprecation")
		String ret = date.getDay() + "-" + date.getMonth() + "-" + date.getYear();
		return ret;
	}

}
