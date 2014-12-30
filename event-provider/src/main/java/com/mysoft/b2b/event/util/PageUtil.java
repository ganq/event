package com.mysoft.b2b.event.util;

public class PageUtil {
	/**
	 * 获取分页偏离量
	 * 
	 * @param page
	 * @param currentPage
	 * @return
	 */
	public static int getPageOffset(int pageSize, int currentPage) {
		if (0 == currentPage || 1 == currentPage) {
			return 0;
		} else {
			if (0 == pageSize) {
				return 10 * (Math.abs(currentPage) - 1);
			} else {
				return Math.abs(pageSize) * (Math.abs(currentPage) - 1);
			}
		}
	}
}
