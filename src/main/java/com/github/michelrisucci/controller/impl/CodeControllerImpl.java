package com.github.michelrisucci.controller.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Named;

import com.github.michelrisucci.controller.CodeController;
import com.github.michelrisucci.jsf.model.Code;

/**
 * @author Michel Risucci
 */
@Named
public class CodeControllerImpl implements CodeController {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.github.michelrisucci.controller.CodeController#list()
	 */
	@Override
	public List<Code> list() {
		List<Code> codes = new ArrayList<Code>();
		codes.addAll(Arrays.asList( //
				new Code(Long.valueOf(1), 111111), //
				new Code(Long.valueOf(2), 222222), //
				new Code(Long.valueOf(3), 333333), //
				new Code(Long.valueOf(4), 444444), //
				new Code(Long.valueOf(5), 555555), //
				new Code(Long.valueOf(6), 666666), //
				new Code(Long.valueOf(7), 777777), //
				new Code(Long.valueOf(8), 888888), //
				new Code(Long.valueOf(9), 999999)));
		return codes;
	}
}