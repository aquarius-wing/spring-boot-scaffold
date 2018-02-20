package com.example.demo.common.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 控制器基类
 * Created by Wing on 2017/2/4.
 */
public abstract class BaseController {

	private final static Logger LOGGER = LoggerFactory.getLogger(BaseController.class);



	/**
	 * 返回jsp视图
	 * @param path
	 * @return
	 */
	public static String jsp(String path) {
		return path.concat(".jsp");
	}



}
